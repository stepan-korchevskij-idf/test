package config.environment

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.reflect.KMutableProperty0

@JsonIgnoreProperties(ignoreUnknown = true)
class EnvironmentConfiguration {
  var user: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::user))
  var pass: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::pass))
  var host: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::host))
  var privateAreaStartEndpoint: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::privateAreaStartEndpoint))
  var crmStartEndpoint: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::crmStartEndpoint))
  var crmUser: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::crmUser))
  var crmPass: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::crmPass))
  var crmCaptcha: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::crmCaptcha))
  var crmSingInEndpoint: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException(::crmSingInEndpoint))

  private fun createMessageException(nameProperty: KMutableProperty0<String?>) =
    "Property '${nameProperty.name}' must be initialized"

  fun getBaseUrlForAuthorisation(): String {
    return "https://$user:$pass@$host"
  }

  fun getBaseUrl(): String {
    return "https://$host"
  }
}