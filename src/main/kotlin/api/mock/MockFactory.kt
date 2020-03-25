package api.mock

class MockFactory {
  fun createMock(stubType: StubType): Mock {
    when (stubType) {
      StubType.MX_LOGIN_CRM__SUCCESS_RESPONSE -> return Mock()
    }
  }
}

