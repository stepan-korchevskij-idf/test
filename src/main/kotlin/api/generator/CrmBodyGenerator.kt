package api.generator

import api.generator.data.AuthoriseForm
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class CrmBodyGenerator(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  private val mapper = ObjectMapper().registerModule(KotlinModule())
  private val jsonMediaType: MediaType = "application/json; charset=utf-8".toMediaType()

  fun authoriseCrm(): RequestBody {
    val authoriseForm = AuthoriseForm(envConfig.crmUser!!, envConfig.crmPass!!, envConfig.crmCaptcha!!)
    return mapper.writeValueAsString(authoriseForm).toRequestBody(jsonMediaType)
  }
}