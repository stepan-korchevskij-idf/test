package context

class DynamicContext {
  val sessionCookie = HashMap<String, String>()

  fun addSessionCookies(cookies: Map<String, String>) {
    sessionCookie.putAll(cookies)
  }
}