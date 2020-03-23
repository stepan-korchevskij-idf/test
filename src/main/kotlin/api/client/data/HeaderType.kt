package api.client.data

enum class HeaderType(val text: String) {
  COOKIE("Set-Cookie"),
  AUTHORIZATION("Authorization"),
  CONTENT_TYPE("Content-Type")
}