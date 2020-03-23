package pages.crm

import org.openqa.selenium.By
import pages.BasePage

class MainCrmPage : BasePage() {
  override var identifierPage: By = By.cssSelector(".login-view.container")
  override var namePage = "Main crm"

  var role: By = By.cssSelector(".header_accountInfo .bd")
}