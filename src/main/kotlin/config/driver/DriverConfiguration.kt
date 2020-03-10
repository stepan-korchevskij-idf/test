package config.driver

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import core.driver.BrowserType
import core.driver.DriverExecutionType

@JsonIgnoreProperties(ignoreUnknown = true)
data class DriverConfiguration(
  var browserType: BrowserType,
  var driverExecutionType: DriverExecutionType,
  var hubUrl: String,
  var implicitlyDefaultTimeoutSeconds: Long,
  var pageLoadedDefaultTimeoutSeconds: Long,
  var scriptDefaultTimeoutSeconds: Long,
  var windowHeight: Int,
  var windowWidth: Int
)