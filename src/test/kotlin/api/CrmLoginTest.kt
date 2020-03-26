package api

import api.client.CustomClient
import api.data.AuthorizedUser
import api.generator.CrmRequestGenerator
import api.mock.MockType
import api.mock.wiremock.WireMockService
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import utils.transformJsonToAny

class CrmLoginTest : WireMockBaseTest() {

  @BeforeAll
  fun createStub() {
    WireMockService().addStub(MockType.MX_LOGIN_CRM_SUCCESS_RESPONSE)
  }

  @Test
  fun checkLogInStub() {
    val codeExpected = 200
    val bodyExpected = AuthorizedUser(1, "admin", "Administrator", 10, null)
    val request = CrmRequestGenerator().getAuthorizeCrmRequest()
    request.url = "$baseUrl${envConfig.crmSingInEndpoint}"
    val response = CustomClient.sendRequest(request)
    assertAll(
      Executable { assertEquals(codeExpected, response.code) },
      Executable { assertEquals(bodyExpected, transformJsonToAny(response.body, AuthorizedUser::class.java)) }
    )
  }
}