import config.driver.DriverConfigProvider
import driver.selenium.DriverFactoryManager
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.util.concurrent.TimeUnit

object CustomDriver {
  private val driverConfiguration = DriverConfigProvider.getConfiguration()
  private val driverThreadLocal = ThreadLocal<WebDriver>()
  private val logger = LogManager.getLogger(this.javaClass.name)

  private fun getDriver(): WebDriver {
    return driverThreadLocal.get() ?: createInstanceDriver()
  }

  private fun createInstanceDriver(): WebDriver {
    logger.info("Creating driver instance")
    val driver = DriverFactoryManager.getDriverFactory(driverConfiguration).getDriver()
    driverThreadLocal.set(driver)
    return driver
  }

  fun get(url: String) {
    logger.info("Navigating to url - '$url'")
    getDriver().get(url)
  }

  fun quit() {
    logger.info("Quit driver")
    getDriver().quit()
    logger.info("Quited driver")
    driverThreadLocal.remove()
  }

  fun findElement(locator: By): WebElement {
    return getDriver().findElement(locator)
  }

  fun findElements(locator: By): MutableList<WebElement> {
    return getDriver().findElements(locator)
  }

  object Timeouts {
    fun implicitlyWait(timeout: Long) {
      getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS)
    }

    fun setDefaultImplicitlyWait() {
      getDriver().manage().timeouts()
        .implicitlyWait(driverConfiguration.implicitlyDefaultTimeoutSeconds, TimeUnit.SECONDS)
    }
  }
}