import com.codeborne.selenide.WebDriverRunner
import config.driver.DriverConfigProvider
import driver.SelenideDriverFactoryManager
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.WebDriver

object SelenideCustomDriver {
  private val driverConfiguration = DriverConfigProvider.getConfiguration()
  private val logger = LogManager.getLogger(this.javaClass.name)

  fun getDriver(): WebDriver {
    if (!WebDriverRunner.hasWebDriverStarted()) {
      createDriver()
    }
    return WebDriverRunner.getWebDriver()
  }

  private fun createDriver() {
    logger.info("Creating driver instance")
    SelenideDriverFactoryManager.getDriverFactory(driverConfiguration).getDriver()
  }

  fun open(url: String) {
    logger.info("Navigating to url - '$url'")
    getDriver().get(url)
  }

  fun quit() {
    logger.info("Quit driver")
    getDriver().quit()
  }
}