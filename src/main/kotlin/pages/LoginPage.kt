package pages

import controls.ButtonElement
import controls.InputElement
import data.User
import org.openqa.selenium.By
import waiters.Waiter

class LoginPage() : BasePage() {
  override var identifierPage: By = By.cssSelector(".login-view.container")
  override var namePage = "Login"

  private val userLogin: By = By.id("login-view--email")
  private val userPassword: By = By.id("password")
  private val loginButton: By = By.name("login-btn")

  fun login(user: User) {
    user.apply {
      typeLogin(login)
      typePassword(password)
    }
    clickLoginButton()
    Waiter.waitInvisibilityElement(CommonLocators.spinner, 10)
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
