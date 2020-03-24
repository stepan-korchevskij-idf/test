package api.client

import api.client.data.ContentType
import api.client.data.HeaderType
import api.client.data.HttpMethod
import okhttp3.*
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import utils.transformDataToJson

object CustomClient : HttpClient {
  private var client: OkHttpClient = OkHttpClient.Builder().build()

  override fun sendRequest(customRequest: CustomRequest): CustomResponse {
    return client.newCall(transformCustomRequestToOkHttpRequest(customRequest)).execute().use {
      transformOkHttpResponseToCustomResponse(it)
    }
  }

  override fun transformOkHttpResponseToCustomResponse(response: Response): CustomResponse {
    return CustomResponse.Builder(response.code)
      .message(response.message)
      .body(response.body?.string())
      .headers(response.headers.toMultimap())
      .build()
  }

  override fun transformCustomRequestToOkHttpRequest(customRequest: CustomRequest): Request {
    return Request.Builder().apply {
        url(customRequest.url)
        customRequest.headers.forEach { addHeader(it.first, it.second) }
        customRequest.credentials?.let {
          addHeader(
            HeaderType.AUTHORIZATION.text,
            Credentials.basic(customRequest.credentials!!.username, customRequest.credentials!!.password)
          )
        }
        when (customRequest.method) {
          HttpMethod.GET -> get()
          HttpMethod.POST -> post(transformAnyToRequestBody(customRequest.body!!))
          HttpMethod.PUT -> put(transformAnyToRequestBody(customRequest.body!!))
          HttpMethod.DELETE -> delete(customRequest.body?.let { transformAnyToRequestBody(it) })
        }
      }
      .build()
  }

  private fun transformAnyToRequestBody(data: Any): RequestBody {
    return transformDataToJson(data).toRequestBody(ContentType.APP_JSON.text.toMediaType())
  }
}
