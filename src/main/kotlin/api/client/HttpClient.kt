package api.client

import okhttp3.Request
import okhttp3.Response

interface HttpClient {
  fun sendRequest(customRequest: CustomRequest): CustomResponse
  fun transformToCustomResponse(response: Response): CustomResponse
  fun transformToOkHttpRequest(customRequest: CustomRequest): Request
}