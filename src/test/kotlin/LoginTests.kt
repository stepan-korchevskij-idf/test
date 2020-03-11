import data.User
import org.junit.jupiter.api.Test
import pages.LoanDetailsPage
import pages.LoginPage
import utils.NavigateOperations

class LoginTests : BaseTest() {

  @Test
  fun checkLogin() {
    val user = User(login = "ta-eqqzghjsuq-0267867945@mail.ru", password = "11111111")
    NavigateOperations.goToEndPoint(environmentConfiguration.privateAreaStartEndpoint, environmentConfiguration, driver)
    LoginPage(driver).apply {
      checkOpened()
      login(user)
    }
    LoanDetailsPage(driver).checkOpened()
  }
}