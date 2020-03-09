package core.driver

import config.DriverConfiguration
import core.utils.SystemPropertiesConfigurator
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class FirefoxDriverManager : DriverManager() {
  override fun createDriver() {
    val options = FirefoxOptions()
    options.addArguments("--window-size=${DriverConfiguration.windowHeight},${DriverConfiguration.windowWidth}")
    val driver: WebDriver
    driver = if (DriverConfiguration.isRemote) {
      RemoteWebDriver(URL(DriverConfiguration.hubUrl), options)
    } else {
      SystemPropertiesConfigurator.add(
        "webdriver.gecko.driver",
        "C:\\Users\\stepan.korchevskij\\browserDriwers\\geckodriver.exe"
      )
      FirefoxDriver(options)
    }
    driverThreadLocal.set(driver)
  }
}