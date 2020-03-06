package core.driver

object DriverManagerFactory {
  fun getManager(type: DriverType): DriverManager {
    return when (type) {
      DriverType.CHROME -> ChromeDriverManager()
      DriverType.FIREFOX -> FirefoxDriverManager()
    }
  }
}