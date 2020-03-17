package pages.crm

import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import pages.BasePage

class MainCrmPage : BasePage() {
  override var identifierPage: By = By.cssSelector(".login-view.container")
  override var namePage = "Main crm"

  var role: By = By.cssSelector(".header_accountInfo .bd")

  fun getRole(): String {
    return `$`(role).text()
  }
}