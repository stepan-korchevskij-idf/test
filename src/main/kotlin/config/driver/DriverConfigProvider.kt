package config.driver

import core.utils.getSelectedClassObjectFromResourceFile

object DriverConfigProvider {
  private const val driverTypeSystemProperty: String = "test.driver.type"
  private const val driverExecutionSystemProperty: String = "test.driver.execution"
  private const val hubUrlSystemPropertySystemProperty: String = "test.driver.url.hub"
  private const val implicitlyDefaultTimeoutSecondsSystemProperty: String = "test.driver.wait.implicitly"
  private const val pageLoadedDefaultTimeoutSecondsSystemProperty: String = "test.driver.wait.page"
  private const val scriptDefaultTimeoutSecondsSystemProperty: String = "test.driver.wait.script"
  private const val windowWidthSystemProperty: String = "test.driver.window.width"
  private const val windowHeightSystemProperty: String = "test.driver.window.height"

  internal fun getConfiguration(): DriverConfiguration {
    return readConfigurationFromFile().apply {
      driverType = getSystemProp(driverTypeSystemProperty) ?: driverType
      driverExecutionType = getSystemProp(driverExecutionSystemProperty) ?: driverExecutionType
      hubUrl = getSystemProp(hubUrlSystemPropertySystemProperty) ?: hubUrl
      implicitlyDefaultTimeoutSeconds =
        getSystemProp(implicitlyDefaultTimeoutSecondsSystemProperty)?.toLong() ?: implicitlyDefaultTimeoutSeconds
      pageLoadedDefaultTimeoutSeconds =
        getSystemProp(pageLoadedDefaultTimeoutSecondsSystemProperty)?.toLong() ?: pageLoadedDefaultTimeoutSeconds
      scriptDefaultTimeoutSeconds =
        getSystemProp(scriptDefaultTimeoutSecondsSystemProperty)?.toLong() ?: scriptDefaultTimeoutSeconds
      windowHeight = getSystemProp(windowWidthSystemProperty)?.toInt() ?: windowHeight
      windowWidth = getSystemProp(windowHeightSystemProperty)?.toInt() ?: windowWidth
    }
  }

  private fun getSystemProp(name: String): String? {
    return System.getProperty(name)
  }

  private fun readConfigurationFromFile(): DriverConfiguration {
    return getSelectedClassObjectFromResourceFile("driverConfiguration.yaml", DriverConfiguration::class.java)!!
  }
}