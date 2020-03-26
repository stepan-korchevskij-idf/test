package config.driver

import ui.driver.BrowserType
import ui.driver.selenium.DriverExecutionType
import utils.ResourceParser
import java.nio.file.NoSuchFileException

object DriverConfigProvider {
  private const val NAME_FILE = "driverConfiguration.yaml"
  private const val BROWSER_TYPE_SYSTEM_PROPERTY = "test.driver.browser.type"
  private const val DRIVER_EXECUTION_SYSTEM_PROPERTY = "test.driver.execution"
  private const val HUB_URL_SYSTEM_PROPERTY = "test.driver.url.hub"
  private const val CHROME_VERSION_SYSTEM_PROPERTY = "test.driver.chrome.version"
  private const val FIREFOX_VERSION_SYSTEM_PROPERTY = "test.driver.firefox.version"
  private const val IMPLICITLY_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY = "test.driver.wait.implicitly"
  private const val PAGE_LOADED_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY = "test.driver.wait.page"
  private const val SCRIPT_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY = "test.driver.wait.script"
  private const val TIMEOUT_SECONDS_SYSTEM_PROPERTY = "test.driver.timeout"
  private const val WINDOW_WIDTH_SYSTEM_PROPERTY = "test.driver.window.width"
  private const val WINDOW_HEIGHT_SYSTEM_PROPERTY = "test.driver.window.height"
  private const val HEADLESS_SYSTEM_PROPERTY = "test.driver.headless"

  fun getConfiguration(): DriverConfiguration {
    return readConfigurationFromFile().apply {
      getSystemProp(BROWSER_TYPE_SYSTEM_PROPERTY)?.apply { browserType = BrowserType.valueOf(this.toUpperCase()) }
      getSystemProp(DRIVER_EXECUTION_SYSTEM_PROPERTY)?.apply {
        driverExecutionType = DriverExecutionType.valueOf(this.toUpperCase())
      }
      getSystemProp(HUB_URL_SYSTEM_PROPERTY)?.apply { hubUrl = this }
      getSystemProp(CHROME_VERSION_SYSTEM_PROPERTY)?.apply { chromeVersion = this }
      getSystemProp(FIREFOX_VERSION_SYSTEM_PROPERTY)?.apply { firefoxVersion = this }
      getSystemProp(IMPLICITLY_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY)
        ?.apply { implicitlyDefaultTimeoutSeconds = this.toLong() }
      getSystemProp(PAGE_LOADED_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY)
        ?.apply { pageLoadedDefaultTimeoutSeconds = this.toLong() }
      getSystemProp(SCRIPT_DEFAULT_TIMEOUT_SECONDS_SYSTEM_PROPERTY)
        ?.apply { scriptDefaultTimeoutSeconds = this.toLong() }
      getSystemProp(TIMEOUT_SECONDS_SYSTEM_PROPERTY)?.apply { timeoutSeconds = this.toLong() }
      getSystemProp(WINDOW_WIDTH_SYSTEM_PROPERTY)?.apply { windowHeight = this.toInt() }
      getSystemProp(WINDOW_HEIGHT_SYSTEM_PROPERTY)?.apply { windowWidth = this.toInt() }
      getSystemProp(HEADLESS_SYSTEM_PROPERTY)?.apply { headless = this.toBoolean() }
    }
  }

  private fun getSystemProp(name: String): String? {
    return System.getProperty(name)
  }

  private fun readConfigurationFromFile(): DriverConfiguration {
    return ResourceParser.getSelectedClassObjectFromYamlFile(NAME_FILE, DriverConfiguration::class.java)
      ?: throw NoSuchFileException(NAME_FILE)
  }
}