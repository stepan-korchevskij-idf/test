package driver

import com.codeborne.selenide.Configuration
import config.driver.DriverConfiguration
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

class SelenideChromeDriverFactory(driverConfiguration: DriverConfiguration) :
  SelenideDefaultDriverFactory(driverConfiguration) {

  override fun createCapability(): DesiredCapabilities {
    Configuration.browserVersion = driverConfiguration.chromeVersion
    return getGeneralDesiredCapabilities().apply {
      merge(ChromeOptions())
    }
  }

  override fun createDriver(desiredCapabilities: DesiredCapabilities) {
    Configuration.browserCapabilities = desiredCapabilities
  }
}