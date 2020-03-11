package config.environment

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EnvironmentConfigurationTest {

  @Test
  fun whenGettingFieldWithNullValue_thenExpectedAssertionThrown() {
    val exception: Exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
      EnvironmentConfiguration().privateAreaStartEndpoint
    }

    val messageExceptionExpected = "Property 'privateAreaStartEndpoint' must be initialized."
    Assertions.assertEquals(messageExceptionExpected, exception.message)
  }
}