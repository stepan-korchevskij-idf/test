package wiremock

import api.client.data.ContentType
import api.client.data.HeaderType
import api.data.AuthorizedUser
import com.github.tomakehurst.wiremock.client.WireMock.*
import config.environment.EnvironmentConfigurationHolder
import utils.transformDataToJson

fun addCrmLoginStub() {
  val envConfig = EnvironmentConfigurationHolder.configuration
  val requestBody = "{\"login\":\"admin\",\"password\":\"11111111\",\"captcha\":\"11111\"}"
  val cookie = "JSESSIONID=testSessionId"
  val authorizedUser = AuthorizedUser(1, "admin", "Administrator", 10, null)

  stubFor(
    post(urlEqualTo(envConfig.crmSingInEndpoint!!))
      .withRequestBody(equalToJson(requestBody))
      .withBasicAuth(envConfig.user, envConfig.pass)
      .withHeader(HeaderType.CONTENT_TYPE.text, equalToIgnoreCase(ContentType.APP_JSON.text))
      .willReturn(
        aResponse()
          .withHeader(HeaderType.CONTENT_TYPE.text, ContentType.APP_JSON.text)
          .withHeader(HeaderType.COOKIE.text, cookie)
          .withBody(transformDataToJson(authorizedUser))
      )
  )
}