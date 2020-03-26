package api.mock.wiremock

import api.mock.BodyPattern
import api.mock.Mock
import api.mock.MockClient
import api.mock.Request
import com.github.tomakehurst.wiremock.client.WireMock

class WireMockClient(
  private val stubsFolder: String = "stub",
  private val filedFolder: String = "__files"
) : MockClient {

  override fun setMock(mock: Mock) {
    val urlMatching = WireMock.urlMatching(mock.request.urlPattern)
    WireMock.stubFor(
      when (mock.request.method) {
        Request.Method.GET -> WireMock.get(urlMatching)
        Request.Method.POST -> WireMock.post(urlMatching)
        Request.Method.DELETE -> WireMock.delete(urlMatching)
        Request.Method.PUT -> WireMock.put(urlMatching)
        Request.Method.ANY -> WireMock.any(urlMatching)
        else -> throw NullPointerException()
      }
        .withName(mock.name)
        .apply {
          mock.priority?.let { atPriority(it) }
          mock.request.bodyPatterns?.forEach { bodyPattern: BodyPattern ->
            bodyPattern.equalToJson?.let { withRequestBody(WireMock.equalToJson(it.toPrettyString())) }
          }
        }
        .willReturn(
          WireMock.aResponse()
            .apply {
              mock.response?.apply {
                status?.let { withStatus(it) }
                bodyFileName?.let { withBody(readBodyFile(it)) }
                headers?.forEach { nameHeader, value -> withHeader(nameHeader, value) }
              }
            })
    )
  }

  private fun readBodyFile(bodyFileName: String): String? {
    return Thread.currentThread().contextClassLoader.getResource("$stubsFolder/$filedFolder/$bodyFileName")
      ?.readText()
  }
}