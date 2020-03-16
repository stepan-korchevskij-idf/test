import listeners.TestListener
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import services.MxPrivateAreaOperations

/**
 * Тест заведомо падающий. Создан только для отладки проекта
 */
class FailedTest : BaseTest() {

  @Test
  @ExtendWith(TestListener::class)
  fun failedTest() {
    MxPrivateAreaOperations().apply {
      openStartPage()
      loanDetailsPage.checkOpened()
    }
  }
}