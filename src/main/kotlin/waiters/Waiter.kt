package waiters

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

object Waiter {
  private const val DEFAULT_IMPLICITLY_TIMEOUT: Long = 10

  fun waitInvisibility(driver: WebDriver, locator: By, timeout: Long) {
    changeImplicitlyTimeout(driver, 0)
    WebDriverWait(driver, timeout)
      .until() {
        try {
          !it.findElement(locator).isDisplayed
        } catch (ex: NoSuchElementException) {
          true
        } catch (ex: StaleElementReferenceException) {
          false
        }
      }
    setDefaultImplicitlyTimeout(driver)
  }

  private fun changeImplicitlyTimeout(driver: WebDriver, implicitlyTimeout: Long) {
    driver.manage().timeouts().implicitlyWait(implicitlyTimeout, TimeUnit.SECONDS)
  }

  private fun setDefaultImplicitlyTimeout(driver: WebDriver) {
    driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICITLY_TIMEOUT, TimeUnit.SECONDS)
  }
}