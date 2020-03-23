package services.crm

import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import services.MxApiOperations
import services.MxUiOperations

class MxCrmOperations(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  val mxUiOperations: MxUiOperations = MxUiOperations(envConfig)
  val mxApiOperations: MxApiOperations = MxApiOperations(envConfig)

  fun openStartPage() {
    mxApiOperations.createAuthorizationSession()
    mxUiOperations.addSessionCookieToBrowser()
    mxUiOperations.openCrmStartPage()
  }
}