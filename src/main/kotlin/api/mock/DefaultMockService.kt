package api.mock

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

open class DefaultMockService(
  protected open val client: MockClient,
  protected open val builder: MockBuilder
) : MockService {
  private val logger: Logger = LogManager.getLogger(this.javaClass.simpleName)

  override fun addStub(mockType: MockType) {
    logger.info("Adding stub - '$mockType'")
    client.setMock(builder.buildMock(mockType))
  }
}

interface MockService {
  fun addStub(mockType: MockType)
}

interface MockClient {
  fun setMock(mock: Mock)
}

interface MockBuilder {
  fun buildMock(mockType: MockType): Mock
}