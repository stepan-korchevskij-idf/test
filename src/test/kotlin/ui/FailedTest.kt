package ui

import org.junit.jupiter.api.Test
import services.MxPrivateAreaOperations

/**
 * Тест заведомо падающий. Создан только для отладки проекта
 */
class FailedTest : BaseTest() {

  @Test
  fun failedTest() {
    MxPrivateAreaOperations().apply {
      openStartPage()
      loanDetailsPage.checkOpened()
    }
  }
}