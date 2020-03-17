package api.generator

import config.environment.EnvironmentConfigurationHolder
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

object RequestBodyGenerator {
  private val environmentConfiguration = EnvironmentConfigurationHolder.configuration
  private val jsonMediaType: MediaType = "application/json; charset=utf-8".toMediaType()

  fun forSuccessfullyCrmAuthorisation(): RequestBody {
    val jsonRequestBody = "{\"login\":\"${environmentConfiguration.crmUser}\"," +
        "\"password\":\"${environmentConfiguration.crmPass}\",\"captcha\":\"${environmentConfiguration.crmCaptcha}\"}"
    return jsonRequestBody.toRequestBody(jsonMediaType)
  }
}