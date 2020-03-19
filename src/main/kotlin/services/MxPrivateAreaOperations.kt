package services

import SelenideCustomDriver
import config.environment.EnvironmentConfigurationHolder
import pages.LoanDetailsPage
import pages.LoginPage

class MxPrivateAreaOperations() {
  val loginPage by lazy { LoginPage() }
  val loanDetailsPage by lazy { LoanDetailsPage() }

  fun openStartPage() {
    SelenideCustomDriver.open(EnvironmentConfigurationHolder.environmentConfiguration.privateAreaStartEndpoint)
  }
}