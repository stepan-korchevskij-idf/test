package driver

import config.driver.DriverConfiguration

object DriverFactoryManager {
  fun getDriverFactory(driverConfiguration: DriverConfiguration): DefaultDriverFactory {
    return when (driverConfiguration.driverExecutionType) {
      DriverExecutionType.REMOTE -> RemoteDriverFactory(driverConfiguration)
      DriverExecutionType.LOCAL -> {
        when (driverConfiguration.browserType) {
          BrowserType.CHROME -> ChromeDriverFactory(driverConfiguration)
          BrowserType.FIREFOX -> FirefoxDriverFactory(driverConfiguration)
        }
      }
    }
  }
}