package utils

import SelenideCustomDriver
import config.environment.EnvironmentConfiguration

object NavigateOperations {

  fun authorize(environmentConfiguration: EnvironmentConfiguration) {
    SelenideCustomDriver.open(environmentConfiguration.getBaseUrl())
  }

  fun goToEndPoint(endpoint: String?, environmentConfiguration: EnvironmentConfiguration) {
    SelenideCustomDriver.open(environmentConfiguration.getApplicationUrl(endpoint!!))
  }
}