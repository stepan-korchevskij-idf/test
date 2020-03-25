package api

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import config.environment.EnvironmentConfigurationHolder
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class WireMockBaseTest {
  private val mockDirectory = "stub"
  private val host = "localhost"
  private lateinit var wireMockServer: WireMockServer
  lateinit var baseUrl: String
  val envConfig = EnvironmentConfigurationHolder.configuration

  @BeforeAll
  fun startWireMockServer() {
    wireMockServer = WireMockServer(
      options().dynamicPort()
        .withRootDirectory(Thread.currentThread().contextClassLoader.getResource(mockDirectory)!!.path)
    )
    wireMockServer.start()
    baseUrl = "http://$host:${wireMockServer.port()}"
    WireMock.configureFor(host, wireMockServer.port())
  }

  @AfterAll
  fun closeWireMockServer() {
    wireMockServer.stop()
  }
}