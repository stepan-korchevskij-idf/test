package wiremock

import api.client.data.ContentType
import api.client.data.HeaderType
import api.data.AuthorizeForm
import api.data.AuthorizedUser
import com.github.tomakehurst.wiremock.client.WireMock.*
import config.environment.EnvironmentConfigurationHolder
import utils.transformDataToJson

fun addCrmLoginStub() {
  val envConfig = EnvironmentConfigurationHolder.configuration
  val cookie = "JSESSIONID=testSessionId"
  val requestBody = AuthorizeForm("admin", "11111111", "11111")
  val authorizedUser = AuthorizedUser(1, "admin", "Administrator", 10, null)

  stubFor(
    post(urlEqualTo(envConfig.crmSingInEndpoint!!))
      .withRequestBody(equalToJson(transformDataToJson(requestBody)))
      .withBasicAuth(envConfig.user, envConfig.pass)//
      .withHeader(HeaderType.CONTENT_TYPE.text, equalToIgnoreCase(ContentType.APP_JSON.text))
      .willReturn(
        aResponse()
          .withHeader(HeaderType.CONTENT_TYPE.text, ContentType.APP_JSON.text)
          .withHeader(HeaderType.COOKIE.text, cookie)//
          .withBody(transformDataToJson(authorizedUser))
      )
  )
}