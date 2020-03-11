package config.environment

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class EnvironmentConfiguration {
  var user: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException("user"))
  var pass: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException("pass"))
  var host: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException("host"))
  var privateAreaStartEndpoint: String? = null
    get() = field ?: throw IllegalArgumentException(createMessageException("privateAreaStartEndpoint"))

  private fun createMessageException(nameProperty: String) = "Property '$nameProperty' must be initialized"

  fun getBaseUrl(): String {
    return "https://$user:$pass@$host"
  }

  fun getApplicationUrl(urlEndpoint: String): String {
    return "https://$host/$urlEndpoint"
  }
}