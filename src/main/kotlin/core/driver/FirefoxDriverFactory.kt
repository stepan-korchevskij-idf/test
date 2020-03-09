package core.driver

import config.driver.DriverConfiguration
import core.utils.SystemPropertiesConfigurator
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class FirefoxDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {

  override fun createDriver() {
    driverThreadLocal.set(initDriver(createCapability()))
  }

  private fun createCapability(): FirefoxOptions {
    return FirefoxOptions().apply {
      addArguments("--window-size=${driverConfiguration.windowHeight},${driverConfiguration.windowWidth}")
    }
  }

  private fun initDriver(options: FirefoxOptions): WebDriver {
    return when (DriverExecutionType.values().first { it.name == driverConfiguration.driverExecutionType }) {
      DriverExecutionType.REMOTE -> RemoteWebDriver(URL(driverConfiguration.hubUrl), options)
      DriverExecutionType.LOCAL -> {
        SystemPropertiesConfigurator.add("webdriver.gecko.driver", "geckodriver.exe")
        FirefoxDriver(options)
      }
    }
  }
}