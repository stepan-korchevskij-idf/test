package api.client

import okhttp3.Request
import okhttp3.Response

interface HttpClient {
  fun sendRequest(customRequest: CustomRequest): CustomResponse
  fun transformOkHttpResponseToCustomResponse(response: Response): CustomResponse
  fun transformCustomRequestToOkHttpRequest(customRequest: CustomRequest): Request
}