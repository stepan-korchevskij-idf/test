import core.BaseTest
import data.User
import org.junit.jupiter.api.Test
import pages.LoanDetailsPage
import pages.LoginPage

class LoginTests : BaseTest() {

  @Test
  fun checkLogin() {
    val user = User(login = "ta-eqqzghjsuq-0267867945@mail.ru", password = "11111111")
    val privateAreaStartEndpoint = configs.getProperty("privateAreaStartEndpoint")
    driver.get(driver.currentUrl + privateAreaStartEndpoint)
    val loginPage = LoginPage(driver)
    loginPage.checkOpened()
    loginPage.login(user)
    LoanDetailsPage(driver).checkOpened()
  }
}