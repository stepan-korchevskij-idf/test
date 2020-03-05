package pages

import core.Waiter
import data.User
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class LoginPage(driver: WebDriver) : BasePage(driver) {
  override var identifierPage: By = By.cssSelector(".login-view.container")
  override var namePage: String = "Login"

  private val userLogin: By = By.id("login-view--email")
  private val userPassword: By = By.id("password")
  private val loginButton: By = By.name("login-btn")

  fun login(user: User) {
    user.apply {
      typeLogin(login)
      typePassword(password)
    }
    clickLoginButton()
    Waiter.waitInvisibility(driver, CommonLocators.spinner, 10)
  }

  private fun typeLogin(login: String) {
    driver.findElement(userLogin).sendKeys(login)
  }

  private fun typePassword(password: String) {
    driver.findElement(userPassword).sendKeys(password)
  }

  private fun clickLoginButton() {
    driver.findElement(loginButton).click()
  }
}