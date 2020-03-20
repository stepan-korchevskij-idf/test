package api.client

import okhttp3.OkHttpClient

object CustomClient : HttpClient {
  private var client: OkHttpClient = OkHttpClient.Builder().build()

  override fun sendRequest(customRequest: CustomRequest): CustomResponse {
    val okhttpResponse = client.newCall(customRequest.generateOkHttpRequest()).execute()
    val customResponse = okhttpResponse.toCustomResponse()
    okhttpResponse.close()
    return customResponse
  }
}