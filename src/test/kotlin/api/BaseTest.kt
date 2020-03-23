package api

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import config.environment.EnvironmentConfigurationHolder
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

open class BaseTest {
  private lateinit var wireMockServer: WireMockServer
  val envConfig = EnvironmentConfigurationHolder.configuration
  val host = "localhost"
  val port: Int = 8080

  @BeforeAll
  fun startWireMockServer() {
    wireMockServer = WireMockServer()
    wireMockServer.start()
    WireMock.configureFor(host, port)
  }

  @AfterAll
  fun closeWireMockServer() {
    wireMockServer.stop()
  }
}