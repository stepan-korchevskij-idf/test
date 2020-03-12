package services

import config.environment.EnvironmentConfiguration
import pages.LoanDetailsPage
import pages.LoginPage
import utils.NavigateOperations

class MxPrivateAreaOperations(private val environmentConfiguration: EnvironmentConfiguration) {
  val loginPage by lazy { LoginPage() }
  val loanDetailsPage by lazy { LoanDetailsPage() }

  fun open() {
    NavigateOperations.goToEndPoint(environmentConfiguration.privateAreaStartEndpoint, environmentConfiguration)
  }
}