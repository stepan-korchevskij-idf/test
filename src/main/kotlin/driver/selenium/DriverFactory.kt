package driver.selenium

import org.openqa.selenium.WebDriver

interface DriverFactory {
  fun getDriver(): WebDriver
}