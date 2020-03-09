package core.driver

import org.openqa.selenium.WebDriver

interface DriverFactory {
  fun getDriver(): WebDriver
}