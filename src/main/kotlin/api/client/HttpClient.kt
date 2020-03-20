package api.client

interface HttpClient {
  fun sendRequest(customRequest: CustomRequest): CustomResponse
}