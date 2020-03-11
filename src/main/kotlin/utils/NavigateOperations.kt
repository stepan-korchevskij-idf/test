package utils

import config.environment.EnvironmentConfiguration
import org.openqa.selenium.WebDriver

object NavigateOperations {
  fun authorize(environmentConfiguration: EnvironmentConfiguration) {
    CustomDriver.go(environmentConfiguration.getBaseUrl())
  }

  fun goToEndPoint(endpoint: String?, environmentConfiguration: EnvironmentConfiguration) {
    CustomDriver.go(environmentConfiguration.getApplicationUrl(endpoint!!))
  }
}