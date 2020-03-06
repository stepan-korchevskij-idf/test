package config

import core.driver.DriverType
import core.utils.loadProperty
import java.util.*

object DriverConfiguration {
  private var property: Properties = loadProperty("driverConfiguration.yaml")
  var driverType: DriverType = DriverType.values().first {
    it.name.equals(getValue("driverType"), ignoreCase = true)
  }
  var isRemote: Boolean = getValue("isRemote").toBoolean()
  var hubUrl: String = getValue("hubUrl")
  var implicitlyDefaultTimeoutSeconds: Long = getValue("implicitlyDefaultTimeoutSeconds").toLong()
  var pageLoadedDefaultTimeoutSeconds: Long = getValue("pageLoadedDefaultTimeoutSeconds").toLong()
  var scriptDefaultTimeoutSeconds: Long = getValue("scriptDefaultTimeoutSeconds").toLong()
  var windowHeight: Int = getValue("windowHeight").toInt()
  var windowWidth: Int = getValue("windowWidth").toInt()

  fun getValue(nameProperty: String): String {
    return System.getProperty(nameProperty, property.getProperty(nameProperty))
  }
}