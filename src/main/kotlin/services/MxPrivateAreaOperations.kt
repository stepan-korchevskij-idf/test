package services

import com.codeborne.selenide.Selenide
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import ui.pages.LoanDetailsPage
import ui.pages.LoginPage

class MxPrivateAreaOperations(
  private val environmentConfiguration: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration
) {
  val loginPage by lazy { LoginPage() }
  val loanDetailsPage by lazy { LoanDetailsPage() }

  fun openStartPage() {
    Selenide.open(environmentConfiguration.privateAreaStartEndpoint)
  }
}