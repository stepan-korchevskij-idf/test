import data.User
import listeners.TestListener
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import services.MxPrivateAreaOperations

class LoginTest : BaseTest() {

  @Test
  @ExtendWith(TestListener::class)
  fun checkLogin() {
    val user = User(login = "ta-eqqzghjsuq-0267867945@mail.ru", password = "11111111")
    MxPrivateAreaOperations().apply {
      openStartPage()
      loginPage.checkOpened()
      loginPage.login(user)
      loanDetailsPage.checkOpened()
    }
  }
}