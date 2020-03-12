package driver

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import config.driver.DriverConfiguration
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities

class SelenideFirefoxDriverFactory(driverConfiguration: DriverConfiguration) :
  SelenideDefaultDriverFactory(driverConfiguration) {

  private val systemPropertyForInitDriver by lazy {
    Pair("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe")
  }

  override fun createCapability(): DesiredCapabilities {
    return getGeneralDesiredCapabilities().apply {
      val firefoxOptions = FirefoxOptions()
      firefoxOptions.profile = getFirefoxProfile()
      merge(firefoxOptions)
    }
  }

  override fun createDriver(desiredCapabilities: DesiredCapabilities): WebDriver {
    initLocalDriverLocation(systemPropertyForInitDriver.first, systemPropertyForInitDriver.second)
    Configuration.browserCapabilities = desiredCapabilities
    Selenide.open()
    return WebDriverRunner.getWebDriver()
  }

  private fun getFirefoxProfile(): FirefoxProfile {
    val profile = FirefoxProfile()
    with(profile) {
      setPreference("capability.policy.default.HTMLDocument.readyState", "allAccess")
      setPreference("capability.policy.default.HTMLDocument.compatMode", "allAccess")
      setPreference("capability.policy.default.Document.compatMode", "allAccess")
      setPreference("capability.policy.default.Location.href", "allAccess")
      setPreference("capability.policy.default.Window.pageXOffset", "allAccess")
      setPreference("capability.policy.default.Window.pageYOffset", "allAccess")
      setPreference("capability.policy.default.Window.frameElement", "allAccess")
      setPreference("capability.policy.default.Window.frameElement.get", "allAccess")
      setPreference("capability.policy.default.Window.QueryInterface", "allAccess")
      setPreference("capability.policy.default.Window.mozInnerScreenY", "allAccess")
      setPreference("capability.policy.default.Window.mozInnerScreenX", "allAccess")
    }
    return profile
  }
}