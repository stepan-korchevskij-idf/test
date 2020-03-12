package driver

import config.driver.DriverConfiguration
import driver.selenium.DriverExecutionType

object SelenideDriverFactoryManager {
  fun getDriverFactory(driverConfiguration: DriverConfiguration): SelenideDefaultDriverFactory {
    return when (driverConfiguration.driverExecutionType) {
      DriverExecutionType.REMOTE -> SelenideRemoteDriverFactory(driverConfiguration)
      DriverExecutionType.LOCAL -> {
        when (driverConfiguration.browserType) {
          BrowserType.CHROME -> SelenideChromeDriverFactory(driverConfiguration)
          BrowserType.FIREFOX -> SelenideFirefoxDriverFactory(driverConfiguration)
        }
      }
    }
  }
}