package core.utils

import config.environment.EnvironmentConfiguration
import org.openqa.selenium.WebDriver

object NavigateOperations {
  internal fun authorize(environmentConfiguration: EnvironmentConfiguration, driver: WebDriver) {
    val baseUrl =
      "https://${environmentConfiguration.user}:${environmentConfiguration.pass}@${environmentConfiguration.host}"
    driver.get(baseUrl)
  }

  internal fun openPrivateAreaOperation(environmentConfiguration: EnvironmentConfiguration, driver: WebDriver) {
    driver.get("https://${environmentConfiguration.host}${environmentConfiguration.privateAreaStartEndpoint}")
  }
}