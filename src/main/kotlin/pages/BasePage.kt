package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

abstract class BasePage(protected var driver: WebDriver) {
  abstract var identifierPage: By
  abstract var namePage: String

  fun checkOpened() {
    val foundedIdentifiers = driver.findElements(identifierPage)
    assert(foundedIdentifiers.size > 0 && foundedIdentifiers[0].isDisplayed) { "$namePage page does not opened." }
  }
}