package api

import api.client.CustomClient
import api.data.AuthorizedUser
import api.generator.CrmRequestGenerator
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import utils.transformJsonToAny
import wiremock.addCrmLoginStub

class CrmLoginTest : WireMockBaseTest() {

  @BeforeAll
  fun createStub() {
    addCrmLoginStub()
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