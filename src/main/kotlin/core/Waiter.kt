package core

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

object Waiter {
  private const val implicitlyTimeout: Long = 10

  fun waitInvisibility(driver: WebDriver, locator: By, timeout: Long) {
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS)
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
    driver.manage().timeouts().implicitlyWait(implicitlyTimeout, TimeUnit.SECONDS)
  }
}