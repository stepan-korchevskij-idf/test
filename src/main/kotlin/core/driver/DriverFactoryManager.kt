package core.driver

import config.driver.DriverConfiguration

object DriverFactoryManager {
  fun getDriverFactory(driverConfiguration: DriverConfiguration): DefaultDriverFactory {
    return when (driverConfiguration.browserType) {
      BrowserType.CHROME -> ChromeDriverFactory(driverConfiguration)
      BrowserType.FIREFOX -> FirefoxDriverFactory(driverConfiguration)
    }
  }
}