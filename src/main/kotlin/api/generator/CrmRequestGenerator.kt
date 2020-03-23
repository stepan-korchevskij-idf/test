package api.generator

import api.client.CustomRequest
import api.client.data.HttpMethod
import api.generator.data.AuthorizeForm
import config.environment.EnvironmentConfiguration

class CrmRequestGenerator(private val envConfig: EnvironmentConfiguration) {

  fun getAuthorizeCrmRequest(): CustomRequest {
    val authorizeForm = AuthorizeForm(envConfig.crmUser!!, envConfig.crmPass!!, envConfig.crmCaptcha!!)
    return CustomRequest.Builder(envConfig.getBaseUrl() + envConfig.crmSingInEndpoint, HttpMethod.POST)
      .credentials(api.client.Credentials(envConfig.user!!, envConfig.pass!!))
      .body(authorizeForm)
      .build()
  }
}