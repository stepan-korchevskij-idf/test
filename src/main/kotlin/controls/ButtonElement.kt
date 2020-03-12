package controls

import com.codeborne.selenide.Selenide.`$`
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.By

object ButtonElement {
  private val logger = LogManager.getLogger(this.javaClass.name)

  fun click(loginButton: By) {
    logger.info("Click button - '$loginButton'")
    `$`(loginButton).click()
  }
}
