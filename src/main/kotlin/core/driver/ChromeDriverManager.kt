package core.driver

import config.DriverConfiguration
import core.utils.SystemPropertiesConfigurator
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class ChromeDriverManager : DriverManager() {
  override fun createDriver() {
    val options = ChromeOptions()
    options.addArguments("--window-size=${DriverConfiguration.windowHeight},${DriverConfiguration.windowWidth}")
    val driver: WebDriver
    driver = if (DriverConfiguration.isRemote) {
      RemoteWebDriver(URL(DriverConfiguration.hubUrl), options)
    } else {
      SystemPropertiesConfigurator.add(
        "webdriver.chrome.driver",
        "C:\\Users\\stepan.korchevskij\\browserDriwers\\chromedriver.exe"
      )
      ChromeDriver(options)
    }
    driverThreadLocal.set(driver)
  }
}