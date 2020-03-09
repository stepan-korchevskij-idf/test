package config.driver

data class DriverConfiguration(
  var driverType: String,
  var driverExecutionType: String,
  var hubUrl: String,
  var implicitlyDefaultTimeoutSeconds: Long,
  var pageLoadedDefaultTimeoutSeconds: Long,
  var scriptDefaultTimeoutSeconds: Long,
  var windowHeight: Int,
  var windowWidth: Int
)