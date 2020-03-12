package utils

import CustomDriver
import config.environment.EnvironmentConfiguration

object NavigateOperations {
  fun authorize(environmentConfiguration: EnvironmentConfiguration) {
    CustomDriver.get(environmentConfiguration.getBaseUrl())
  }

  fun goToEndPoint(endpoint: String?, environmentConfiguration: EnvironmentConfiguration) {
    CustomDriver.get(environmentConfiguration.getApplicationUrl(endpoint!!))
  }
}