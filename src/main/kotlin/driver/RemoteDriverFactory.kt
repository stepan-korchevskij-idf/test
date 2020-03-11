package driver

import config.driver.DriverConfiguration
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class RemoteDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {

  override fun createDriver(capabilities: Capabilities): WebDriver {
    return RemoteWebDriver(URL(driverConfiguration.hubUrl), capabilities)
  }

  override fun createCapability(): Capabilities {
    return when (driverConfiguration.browserType) {
      BrowserType.CHROME -> ChromeDriverFactory(driverConfiguration).createCapability()
      BrowserType.FIREFOX -> FirefoxDriverFactory(driverConfiguration).createCapability()
    }
  }
}
