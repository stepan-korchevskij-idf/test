package ui.pages

import org.openqa.selenium.By

class LoanDetailsPage() : BasePage() {
  override var identifierPage: By = By.cssSelector(".private-office-common-view.container")
  override var namePage = "Loan details"
}