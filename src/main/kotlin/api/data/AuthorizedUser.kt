package api.data

data class AuthorizedUser(
  val id: Int?,
  val userName: String?,
  val localizedRole: String?,
  val roleId: Int?,
  val error: String?
)