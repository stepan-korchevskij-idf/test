package api.mock

import api.client.data.HttpMethod.*
import com.github.tomakehurst.wiremock.client.WireMock.*

class CustomMockService : MockService {
  override fun addStub(mock: Mock) {
    //todo extract into small methods
    val urlMatching = urlMatching(mock.request.urlPattern)
    stubFor(
      when (mock.httpMethod) {
        GET -> get(urlMatching)
        POST -> post(urlMatching)
        DELETE -> delete(urlMatching)
        PUT -> put(urlMatching)
        else -> any(urlMatching)
      }
        .withName(mock.name)
        .apply {
          mock.priority?.let { atPriority(it) }
          mock.request.apply {
            withRequestBody(equalToJson(bodyPatterns))
            headers.forEach { (nameHeader: String, headers: List<String>) ->
              headers.forEach { header -> withHeader(nameHeader, containing(header)) }
            }
          }
        }
        .willReturn(
          aResponse()
            .withStatus(mock.response.status)
            .apply {
              mock.response.apply {
                withBody(body)
                headers.forEach { (nameHeader: String, headers: List<String>) ->
                  headers.forEach { header -> withHeader(nameHeader, header) }
                }
              }
            })
    )
  }
}

interface MockService {
  fun addStub(mock: Mock)
}