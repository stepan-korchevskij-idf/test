package driver

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import config.driver.DriverConfiguration
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

class SelenideChromeDriverFactory(driverConfiguration: DriverConfiguration) :
  SelenideDefaultDriverFactory(driverConfiguration) {

  private val systemPropertyForInitDriver by lazy {
    Pair("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe")
  }

  override fun createCapability(): DesiredCapabilities {
    return getGeneralDesiredCapabilities().apply {
      merge(ChromeOptions())
    }
  }

  override fun createDriver(desiredCapabilities: DesiredCapabilities): WebDriver {
    initLocalDriverLocation(systemPropertyForInitDriver.first, systemPropertyForInitDriver.second)
    Configuration.browserCapabilities = desiredCapabilities
    Selenide.open()
    return WebDriverRunner.getWebDriver()
  }
}