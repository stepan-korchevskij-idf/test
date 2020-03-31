package ui

import data.User
import dataProviders.CorrectUsersArgumentsProvider
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import services.priveteArea.MxPrivateAreaOperations

class LoginParameterizedTest : BaseTest() {

  @ParameterizedTest(name = "Check login. Test №{index}")
  @ArgumentsSource(CorrectUsersArgumentsProvider::class)
  fun checkLogin(user: User) {
    MxPrivateAreaOperations().apply {
      openStartPage()
      loginPage.checkOpened()
      loginPage.login(user)
      loanDetailsPage.checkOpened()
    }
  }
}