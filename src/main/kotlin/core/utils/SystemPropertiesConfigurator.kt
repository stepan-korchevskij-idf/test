package core.utils

object SystemPropertiesConfigurator {
  private var properties = HashMap<String, String>()

  internal fun add(key: String, value: String) {
    properties[key] = value
    System.setProperty(key, value)
  }

  internal fun clearAll() {
    properties.forEach { (key, _) -> System.clearProperty(key) }
    properties = HashMap<String, String>()
  }
}