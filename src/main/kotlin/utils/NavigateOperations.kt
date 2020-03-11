package utils

import config.environment.EnvironmentConfiguration
import org.openqa.selenium.WebDriver

object NavigateOperations {
  fun authorize(environmentConfiguration: EnvironmentConfiguration, driver: WebDriver) {
    driver.get(environmentConfiguration.getBaseUrl())
  }

  fun goToEndPoint(endpoint: String?, environmentConfiguration: EnvironmentConfiguration, driver: WebDriver) {
    driver.get(environmentConfiguration.getApplicationUrl(endpoint!!))
  }
}