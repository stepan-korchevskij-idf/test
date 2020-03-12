package driver

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import config.driver.DriverConfiguration
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities

class SelenideRemoteDriverFactory(driverConfiguration: DriverConfiguration) : SelenideDefaultDriverFactory
  (driverConfiguration) {

  override fun createDriver(desiredCapabilities: DesiredCapabilities): WebDriver {
    Configuration.remote = driverConfiguration.hubUrl
    Configuration.browserCapabilities = desiredCapabilities
    Selenide.open()
    return WebDriverRunner.getWebDriver()
  }

  override fun createCapability(): DesiredCapabilities {
    return when (driverConfiguration.browserType) {
      BrowserType.CHROME -> SelenideChromeDriverFactory(driverConfiguration).createCapability()
      BrowserType.FIREFOX -> SelenideFirefoxDriverFactory(driverConfiguration).createCapability()
    }
  }
}
