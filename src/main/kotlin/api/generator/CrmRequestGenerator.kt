package api.generator

import api.client.CustomRequest
import api.client.data.HttpMethod
import api.generator.data.AuthoriseForm
import config.environment.EnvironmentConfiguration

class CrmRequestGenerator(private val envConfig: EnvironmentConfiguration) {

  fun getAuthorizeCrmRequest(): CustomRequest {
    val authoriseForm = AuthoriseForm(envConfig.crmUser!!, envConfig.crmPass!!, envConfig.crmCaptcha!!)
    return CustomRequest.Builder(envConfig.getBaseUrl() + envConfig.crmSingInEndpoint, HttpMethod.POST)
      .credentials(api.client.Credentials(envConfig.user!!, envConfig.pass!!))
      .body(authoriseForm)
      .build()
  }
}