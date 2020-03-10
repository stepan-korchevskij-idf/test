package core.utils

import config.environment.EnvironmentConfiguration
import org.openqa.selenium.WebDriver

object NavigateOperations {
  internal fun authorize(environmentConfiguration: EnvironmentConfiguration, driver: WebDriver) {
    driver.get(environmentConfiguration.getBaseUrl())
  }

  internal fun goToEndPoint(endpoint: String?, environmentConfiguration: EnvironmentConfiguration, driver: WebDriver) {
    driver.get(environmentConfiguration.getApplicationUrl(endpoint!!))
  }
}