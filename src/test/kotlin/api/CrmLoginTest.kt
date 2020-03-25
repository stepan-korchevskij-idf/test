package api

import api.client.CustomClient
import api.data.AuthorizedUser
import api.generator.CrmRequestGenerator
import api.mock.CustomMockService
import api.mock.StubType
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import utils.getMockObjectFromResourceFile
import utils.transformJsonToAny

class CrmLoginTest : WireMockBaseTest() {

  @BeforeAll
  fun createStub() {
    val mock = getMockObjectFromResourceFile(StubType.MX_LOGIN_CRM__SUCCESS_RESPONSE)
    CustomMockService.addStub(mock)
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