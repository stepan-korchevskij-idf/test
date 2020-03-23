package api

import api.client.CustomClient
import api.client.data.ContentType
import api.client.data.CookieType
import api.client.data.HeaderType
import api.generator.CrmRequestGenerator
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import config.environment.EnvironmentConfigurationHolder
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import utils.transformDataToJson

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmLoginTest {
  private lateinit var wireMockServer: WireMockServer
  private val envConfig = EnvironmentConfigurationHolder.configuration

  private val host = "localhost"
  private val port: Int = 8080
  private val endpoint: String = envConfig.crmSingInEndpoint!!

  private val cookie = "JSESSIONID=testSessionId"
  private val requestBody = "{\"login\":\"admin\",\"password\":\"11111111\"," +
      "\"captcha\":\"11111\"}"
  private val responseBody =
    "{\"id\":1,\"userName\":\"admin\",\"localizedRole\":\"Administrator\",\"roleId\":10,\"error\":null}"

  @BeforeAll
  fun createStub() {
    wireMockServer = WireMockServer()
    wireMockServer.start()
    configureFor(host, port)
    stubFor(
      post(urlEqualTo(endpoint))
        .withRequestBody(equalToJson(requestBody))
        .withBasicAuth(envConfig.user, envConfig.pass)
        .withHeader(HeaderType.CONTENT_TYPE.text, equalToIgnoreCase(ContentType.APP_JSON.text))
        .willReturn(
          aResponse()
            .withHeader("Content-Type", ContentType.APP_JSON.text)
            .withHeader("Set-Cookie", cookie)
            .withBody(responseBody)
        )
    )
  }

  @Test
  fun checkLogInStub() {
    val codeExpected = 200
    val bodyExpected = transformDataToJson(User(1, "admin", "Administrator", 10, null))
    val request = CrmRequestGenerator().getAuthorizeCrmRequest()
    request.url = "http://$host:$port$endpoint"
    val response = CustomClient.sendRequest(request)
    assertAll(
      Executable { assertEquals(codeExpected, response.code) },
      Executable { assertEquals(bodyExpected, response.body) },
      Executable { assert(response.getCookies().contains(CookieType.JSESSIONID.text)) }
    )
  }
}

data class User(
  val id: Int?,
  val userName: String?,
  val localizedRole: String?,
  val roleId: Int?,
  val error: String?
)