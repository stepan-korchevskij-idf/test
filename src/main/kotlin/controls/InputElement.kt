package controls

import CustomDriver
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.By

object InputElement {
  private val logger = LogManager.getLogger(this.javaClass.name)

  fun type(locator: By, text: String) {
    logger.log(Level.INFO, "Typing text - '$text' in input - '$locator'")
    CustomDriver.findElement(locator).sendKeys(text)
  }
}