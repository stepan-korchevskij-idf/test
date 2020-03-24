package api

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import config.environment.EnvironmentConfigurationHolder
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class WireMockBaseTest {
  private lateinit var wireMockServer: WireMockServer
  private val host = "localhost"
  private val port: Int = 8080
  val envConfig = EnvironmentConfigurationHolder.configuration
  val baseUrl = "http://$host:$port"

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