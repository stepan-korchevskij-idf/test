package ui

import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.WebDriverRunner
import config.driver.DriverConfigProvider
import config.environment.EnvironmentConfigurationHolder
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import ui.driver.SelenideDriverFactoryManager

object SelenideCustomDriver {
  private val driverConfiguration = DriverConfigProvider.getConfiguration()
  private val logger = LogManager.getLogger(this.javaClass.name)

  fun getDriver(): WebDriver {
    return WebDriverRunner.getWebDriver()
  }

  fun configureDriver() {
    logger.info("Configuring driver")
    SelenideDriverFactoryManager.getDriverFactory(driverConfiguration).getDriver()
  }

  fun quit() {
    logger.info("Quit driver")
    getDriver().quit()
  }

  fun authorize() {
    open(EnvironmentConfigurationHolder.configuration.getBaseUrlForAuthorisation())
  }

  fun deleteCookie(name: String) {
    logger.info("Deleting cookie: '$name'")
    getDriver().manage().deleteCookieNamed(name)
  }

  fun addCookie(name: String, value: String) {
    logger.info("Adding cookie: '$name': '$value'")
    getDriver().manage().addCookie(Cookie(name, value))
  }
}