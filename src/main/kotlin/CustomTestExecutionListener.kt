import org.apache.logging.log4j.LogManager
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import utils.takeScreenshot

class CustomTestExecutionListener : TestExecutionListener {
  private val logger = LogManager.getLogger(this.javaClass.name)

  override fun executionFinished(testIdentifier: TestIdentifier?, testExecutionResult: TestExecutionResult?) {
    super.executionFinished(testIdentifier, testExecutionResult)
    testIdentifier?.apply {
      logger.info("Taking screenshot")
      takeScreenshot(displayName)
    }
  }
}