package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class LoanDetailsPage(driver: WebDriver) : BasePage(driver) {
  override var identifierPage: By = By.cssSelector(".private-office-common-view.container")
  override var namePage: String = "Loan details"
}