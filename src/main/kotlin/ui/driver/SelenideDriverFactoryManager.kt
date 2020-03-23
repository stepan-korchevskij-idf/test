package ui.driver

import config.driver.DriverConfiguration
import org.apache.logging.log4j.LogManager
import ui.driver.selenium.DriverExecutionType

object SelenideDriverFactoryManager {
  fun getDriverFactory(driverConfiguration: DriverConfiguration): SelenideDefaultDriverFactory {
    LogManager.getLogger(this.javaClass.simpleName).info(driverConfiguration)
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