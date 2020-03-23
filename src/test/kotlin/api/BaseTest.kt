package api

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

class BaseTest {
  private lateinit var wireMockServer: WireMockServer
  val host = "localhost"
  val port: Int = 8080

  @BeforeAll
  fun createStub() {
    wireMockServer = WireMockServer()
    wireMockServer.start()
    WireMock.configureFor(host, port)
  }

  @AfterAll
  fun closeWireMockServer() {
    wireMockServer.stop()
  }
}