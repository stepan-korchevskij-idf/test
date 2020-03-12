import data.User
import org.junit.jupiter.api.Test
import services.MxPrivateAreaOperations

class LoginTests : BaseTest() {

  @Test
  fun checkLogin() {
    val user = User(login = "ta-eqqzghjsuq-0267867945@mail.ru", password = "11111111")
    MxPrivateAreaOperations(environmentConfiguration).apply {
      open()
      loginPage.checkOpened()
      loginPage.login(user)
      loanDetailsPage.checkOpened()
    }
  }
}