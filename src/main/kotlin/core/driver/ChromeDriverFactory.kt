package core.driver

import config.driver.DriverConfiguration
import core.utils.SystemPropertiesConfigurator
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class ChromeDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {
  override fun createDriver() {
    driverThreadLocal.set(initDriver(createCapability()))
  }

  private fun createCapability(): ChromeOptions {
    return ChromeOptions().apply {
      addArguments("--window-size=${driverConfiguration.windowHeight},${driverConfiguration.windowWidth}")
    }
  }

  private fun initDriver(options: ChromeOptions): WebDriver {
    return when (DriverExecutionType.values().first { it.name == driverConfiguration.driverExecutionType }) {
      DriverExecutionType.REMOTE -> RemoteWebDriver(URL(driverConfiguration.hubUrl), options)
      DriverExecutionType.LOCAL -> {
        SystemPropertiesConfigurator.add("webdriver.chrome.driver", "chromedriver.exe")
        ChromeDriver(options)
      }
    }
  }
}