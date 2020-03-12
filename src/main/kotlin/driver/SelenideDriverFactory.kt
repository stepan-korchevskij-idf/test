package driver

import com.codeborne.selenide.SelenideDriver

interface SelenideDriverFactory {
  fun getDriver(): SelenideDriver
}