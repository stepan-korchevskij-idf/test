package config.driver

import driver.BrowserType
import driver.selenium.DriverExecutionType
import utils.getSelectedClassObjectFromResourceFile
import java.nio.file.NoSuchFileException

object DriverConfigProvider {
  private const val NAME_FILE = "driverConfiguration.yaml"
  private const val BROWSER_TYPE_SYSTEM_PROPERTY = "test.driver.browser.type"
  private const val DRIVER_EXECUTION_SYSTEM_PROPERTY = "test.driver.execution"
  private const val HUB_URL_SYSTEM_PROPERTY = "test.driver.url.hub"
  private const val IMPLICITLY_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY = "test.driver.wait.implicitly"
  private const val PAGE_LOADED_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY = "test.driver.wait.page"
  private const val SCRIPT_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY = "test.driver.wait.script"
  private const val WINDOW_WIDTH_SYSTEM_PROPERTY = "test.driver.window.width"
  private const val WINDOW_HEIGHT_SYSTEM_PROPERTY = "test.driver.window.height"

  fun getConfiguration(): DriverConfiguration {
    return readConfigurationFromFile().apply {
      getSystemProp(BROWSER_TYPE_SYSTEM_PROPERTY)?.apply { browserType = BrowserType.valueOf(this.toUpperCase()) }
      getSystemProp(DRIVER_EXECUTION_SYSTEM_PROPERTY)?.apply {
        driverExecutionType = DriverExecutionType.valueOf(this.toUpperCase())
      }
      getSystemProp(HUB_URL_SYSTEM_PROPERTY)?.apply { hubUrl = this }
      getSystemProp(IMPLICITLY_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY)
        ?.apply { implicitlyDefaultTimeoutSeconds = this.toLong() }
      getSystemProp(PAGE_LOADED_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY)
        ?.apply { pageLoadedDefaultTimeoutSeconds = this.toLong() }
      getSystemProp(SCRIPT_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY)
        ?.apply { scriptDefaultTimeoutSeconds = this.toLong() }
      getSystemProp(WINDOW_WIDTH_SYSTEM_PROPERTY)?.apply { windowHeight = this.toInt() }
      getSystemProp(WINDOW_HEIGHT_SYSTEM_PROPERTY)?.apply { windowWidth = this.toInt() }
    }
  }

  private fun getSystemProp(name: String): String? {
    return System.getProperty(name)
  }

  private fun readConfigurationFromFile(): DriverConfiguration {
    return getSelectedClassObjectFromResourceFile(NAME_FILE, DriverConfiguration::class.java)
      ?: throw NoSuchFileException(NAME_FILE)
  }
}