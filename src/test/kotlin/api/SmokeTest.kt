package api

import api.generator.CrmBodyGenerator
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import okhttp3.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginCrmTest {
  private lateinit var environmentConfiguration: EnvironmentConfiguration
  private lateinit var client: OkHttpClient

  @BeforeAll
  fun init() {
    environmentConfiguration = EnvironmentConfigurationHolder.configuration
    client = OkHttpClient.Builder()
      .build()
  }

  @Test
  fun whenGetLoginPage_thenStatus200() {
    val request: Request = Request.Builder()
      .url(environmentConfiguration.getBaseUrl() + environmentConfiguration.privateAreaStartEndpoint)
      .build()
    val call = client.newCall(request)
    val response = call.execute()
    Assertions.assertEquals(200, response.code)
  }

  @Test
  fun whenGetCrmLoginPage_thenStatus200() {
    val request: Request = Request.Builder()
      .url(environmentConfiguration.getBaseUrl() + environmentConfiguration.crmStartEndpoint)
      .addHeader(
        "Authorization",
        Credentials.basic(
          EnvironmentConfigurationHolder.configuration.user!!,
          EnvironmentConfigurationHolder.configuration.pass!!
        )
      )
      .build()
    val call = client.newCall(request)
    val response = call.execute()
    Assertions.assertEquals(200, response.code)
  }

  @Test
  fun whenGetCrmLoginPageWithErrorCredentials_thenStatus401() {
    val username = "incorrectUsername"
    val password = "incorrectPassword"
    val request: Request = Request.Builder()
      .url(environmentConfiguration.getBaseUrl() + environmentConfiguration.crmStartEndpoint)
      .addHeader("Authorization", Credentials.basic(username, password))
      .build()
    val call = client.newCall(request)
    val response = call.execute()
    Assertions.assertEquals(401, response.code)
  }

  @Test
  fun whenSendPostRequestWithAuthorization_thenStatus200() {
    val request: Request = Request.Builder()
      .url(environmentConfiguration.getBaseUrl() + environmentConfiguration.crmSingInEndpoint)
      .addHeader("Authorization", Credentials.basic(environmentConfiguration.user!!, environmentConfiguration.pass!!))
      .post(CrmBodyGenerator(environmentConfiguration).authoriseCrm())
      .build()
    val call: Call = client.newCall(request)
    val response: Response = call.execute()
    Assertions.assertEquals(200, response.code)
  }
}