package api.mock

import api.mock.Request.Method.*
import com.github.tomakehurst.wiremock.client.WireMock.*

object CustomMockService : MockService {

  override fun addStub(mock: Mock) {
    val urlMatching = urlMatching(mock.request.urlPattern)
    stubFor(
      when (mock.request.method) {
        GET -> get(urlMatching)
        POST -> post(urlMatching)
        DELETE -> delete(urlMatching)
        PUT -> put(urlMatching)
        ANY -> any(urlMatching)
        else -> throw NullPointerException()
      }
        .withName(mock.name)
        .apply {
          mock.priority?.let { atPriority(it) }
          mock.request.bodyPatterns?.forEach { bodyPattern: BodyPattern ->
            bodyPattern.equalToJson?.let { withRequestBody(equalToJson(it.toPrettyString())) }
          }
        }
        .willReturn(
          aResponse()
            .apply {
              mock.response?.apply {
                status?.let { withStatus(it) }
                bodyFileName?.let { withBodyFile(it) }
                headers?.forEach { nameHeader, value -> withHeader(nameHeader, value) }
              }
            })
    )
  }
}

interface MockService {
  fun addStub(mock: Mock)
}