package config.driver

import core.driver.BrowserType
import core.driver.DriverExecutionType
import core.utils.getSelectedClassObjectFromResourceFile
import java.nio.file.NoSuchFileException

object DriverConfigProvider {
  private const val nameFile = "driverConfiguration.yaml"
  private const val browserTypeSystemProperty: String = "test.driver.browser.type"
  private const val driverExecutionSystemProperty: String = "test.driver.execution"
  private const val hubUrlSystemPropertySystemProperty: String = "test.driver.url.hub"
  private const val implicitlyDefaultTimeoutSecondsSystemProperty: String = "test.driver.wait.implicitly"
  private const val pageLoadedDefaultTimeoutSecondsSystemProperty: String = "test.driver.wait.page"
  private const val scriptDefaultTimeoutSecondsSystemProperty: String = "test.driver.wait.script"
  private const val windowWidthSystemProperty: String = "test.driver.window.width"
  private const val windowHeightSystemProperty: String = "test.driver.window.height"

  internal fun getConfiguration(): DriverConfiguration {
    return readConfigurationFromFile().apply {
      getSystemProp(browserTypeSystemProperty)?.apply { browserType = BrowserType.valueOf(this.toUpperCase()) }
      getSystemProp(driverExecutionSystemProperty)?.apply {
        driverExecutionType = DriverExecutionType.valueOf(this.toUpperCase())
      }
      getSystemProp(hubUrlSystemPropertySystemProperty)?.apply { hubUrl = this }
      getSystemProp(implicitlyDefaultTimeoutSecondsSystemProperty)?.toLong()
        ?.apply { implicitlyDefaultTimeoutSeconds = this }
      getSystemProp(pageLoadedDefaultTimeoutSecondsSystemProperty)?.toLong()
        ?.apply { pageLoadedDefaultTimeoutSeconds = this }
      getSystemProp(scriptDefaultTimeoutSecondsSystemProperty)?.toLong()?.apply { scriptDefaultTimeoutSeconds = this }
      getSystemProp(windowWidthSystemProperty)?.toInt()?.apply { windowHeight = this }
      getSystemProp(windowHeightSystemProperty)?.toInt()?.apply { windowWidth = this }
    }
  }

  private fun getSystemProp(name: String): String? {
    return System.getProperty(name)
  }

  private fun readConfigurationFromFile(): DriverConfiguration {
    return getSelectedClassObjectFromResourceFile(nameFile, DriverConfiguration::class.java)
      ?: throw NoSuchFileException(nameFile)
  }
}