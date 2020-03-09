package config.environment

import core.utils.getSelectedClassObjectFromResourceFile

object EnvironmentConfigProvider {
  private const val userSystemProperty: String = "test.env.user"
  private const val passSystemProperty: String = "test.env.pass"
  private const val hostSystemProperty: String = "test.env.host"
  private const val privateAreaStartEndpointSystemProperty: String = "test.env.endpoint.private.area"

  internal fun getConfiguration(): EnvironmentConfiguration {
    return readConfigurationFromFile().apply {
      user = getSystemProp(userSystemProperty) ?: user
      pass = getSystemProp(passSystemProperty) ?: pass
      host = getSystemProp(hostSystemProperty) ?: host
      privateAreaStartEndpoint = getSystemProp(privateAreaStartEndpointSystemProperty) ?: privateAreaStartEndpoint
    }
  }

  private fun readConfigurationFromFile(): EnvironmentConfiguration {
    return getSelectedClassObjectFromResourceFile(
      "environmentConfiguration.yaml",
      EnvironmentConfiguration::class.java
    )!!
  }

  private fun getSystemProp(name: String): String? {
    return System.getProperty(name)
  }
}