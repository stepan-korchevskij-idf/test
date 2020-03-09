package core.driver

import config.driver.DriverConfiguration

object DriverFactoryManager {
  fun getDriverFactory(driverConfiguration: DriverConfiguration): DefaultDriverFactory {
    return when (DriverType.values().first { it.name == driverConfiguration.driverType }) {
      DriverType.CHROME -> ChromeDriverFactory(driverConfiguration)
      DriverType.FIREFOX -> FirefoxDriverFactory(driverConfiguration)
    }
  }
}