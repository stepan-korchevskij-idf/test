package services

import com.codeborne.selenide.Selenide
import config.environment.EnvironmentConfigurationHolder
import pages.LoanDetailsPage
import pages.LoginPage

class MxPrivateAreaOperations() {
  val loginPage by lazy { LoginPage() }
  val loanDetailsPage by lazy { LoanDetailsPage() }

  fun openStartPage() {
    Selenide.open(EnvironmentConfigurationHolder.configuration.privateAreaStartEndpoint)
  }
}