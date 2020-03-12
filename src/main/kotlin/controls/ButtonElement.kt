package controls

import CustomDriver
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.By

object ButtonElement {
  private val logger = LogManager.getLogger(this.javaClass.name)

  fun click(loginButton: By) {
    logger.log(Level.INFO, "Click button - '$loginButton'")
    CustomDriver.findElement(loginButton).click()
  }
}
