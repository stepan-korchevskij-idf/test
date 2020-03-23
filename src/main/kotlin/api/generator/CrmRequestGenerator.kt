package api.generator

import api.client.CustomRequest
import api.client.data.HttpMethod
import api.data.AuthorizeForm
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder

class CrmRequestGenerator(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {

  fun getAuthorizeCrmRequest(): CustomRequest {
    val authorizeForm = AuthorizeForm(envConfig.crmUser!!, envConfig.crmPass!!, envConfig.crmCaptcha!!)
    return CustomRequest.Builder(envConfig.getBaseUrl() + envConfig.crmSingInEndpoint!!, HttpMethod.POST)
      .credentials(api.client.Credentials(envConfig.user!!, envConfig.pass!!))
      .body(authorizeForm)
      .build()
  }
}