package ui.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import data.User
import org.openqa.selenium.By
import ui.controls.ButtonElement
import ui.controls.InputElement

class LoginPage() : BasePage() {
  override var identifierPage: By = By.cssSelector(".login-view.container")
  override var namePage = "Login"

  private val userLogin: By = By.id("login-view--email")
  private val userPassword: By = By.id("password")
  private val loginButton: By = By.name("login-btn")

  fun login(user: User) {
    user.apply {
      typeLogin(login!!)
      typePassword(privateAreaPassword!!)
    }
    clickLoginButton()
    `$`(CommonLocators.spinner).shouldNotBe(Condition.visible)
  }

  private fun typeLogin(login: String) {
    InputElement.type(userLogin, login)
  }

  private fun typePassword(password: String) {
    InputElement.type(userPassword, password)
  }

  private fun clickLoginButton() {
    ButtonElement.click(loginButton)
  }
}
