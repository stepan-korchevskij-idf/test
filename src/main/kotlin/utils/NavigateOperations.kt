package utils

import SelenideCustomDriver
import config.environment.EnvironmentConfiguration

object NavigateOperations {
  fun authorize(environmentConfiguration: EnvironmentConfiguration) {
    SelenideCustomDriver.get(environmentConfiguration.getBaseUrl())
  }

  fun goToEndPoint(endpoint: String?, environmentConfiguration: EnvironmentConfiguration) {
    SelenideCustomDriver.get(environmentConfiguration.getApplicationUrl(endpoint!!))
  }
}