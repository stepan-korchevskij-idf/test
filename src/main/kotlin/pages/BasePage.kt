package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By

abstract class BasePage() {
  abstract var identifierPage: By
  abstract var namePage: String

  fun checkOpened() {
    `$`(identifierPage).shouldBe(Condition.visible)
  }
}