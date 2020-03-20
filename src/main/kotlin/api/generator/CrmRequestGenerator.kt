package api.generator

import api.client.CustomRequest
import api.client.data.HeaderType
import api.client.data.HttpMethod
import config.environment.EnvironmentConfiguration
import okhttp3.Credentials

class CrmRequestGenerator(private val envConfig: EnvironmentConfiguration) {

  fun getAuthorizeCrmRequest(): CustomRequest {
    return CustomRequest.Builder()
      .url(envConfig.getBaseUrl() + envConfig.crmSingInEndpoint)
      .addHeader(HeaderType.AUTHORIZATION.text, Credentials.basic(envConfig.user!!, envConfig.pass!!))
      .body(CrmBodyGenerator(envConfig).authorizeCrm())
      .method(HttpMethod.POST)
      .build()
  }
}