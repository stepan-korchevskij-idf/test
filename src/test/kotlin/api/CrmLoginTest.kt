package api

import api.client.CustomClient
import api.client.data.CookieType
import api.data.AuthorizedUser
import api.generator.CrmRequestGenerator
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import utils.transformDataToJson
import wiremock.addCrmLoginStub

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CrmLoginTest : BaseTest() {

  @BeforeAll
  fun createStub() {
    addCrmLoginStub()
  }

  @Test
  fun checkLogInStub() {
    val codeExpected = 200
    val bodyExpected = transformDataToJson(AuthorizedUser(1, "admin", "Administrator", 10, null))
    val request = CrmRequestGenerator().getAuthorizeCrmRequest()
    request.url = "http://$host:$port${envConfig.crmSingInEndpoint}"
    val response = CustomClient.sendRequest(request)
    assertAll(
      Executable { assertEquals(codeExpected, response.code) },
      Executable { assertEquals(bodyExpected, response.body) },
      Executable { assert(response.getCookies().contains(CookieType.JSESSIONID.text)) }
    )
  }
}