package driver

import com.codeborne.selenide.Configuration
import config.driver.DriverConfiguration
import org.openqa.selenium.remote.DesiredCapabilities

class SelenideRemoteDriverFactory(driverConfiguration: DriverConfiguration) : SelenideDefaultDriverFactory
  (driverConfiguration) {

  override fun createDriver(desiredCapabilities: DesiredCapabilities) {
    Configuration.remote = driverConfiguration.hubUrl
    Configuration.browserCapabilities = desiredCapabilities
  }

  override fun createCapability(): DesiredCapabilities {
    return when (driverConfiguration.browserType) {
      BrowserType.CHROME -> SelenideChromeDriverFactory(driverConfiguration).createCapability()
      BrowserType.FIREFOX -> SelenideFirefoxDriverFactory(driverConfiguration).createCapability()
    }
  }
}