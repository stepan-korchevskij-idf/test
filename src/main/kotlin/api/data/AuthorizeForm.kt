package api.data

data class AuthorizeForm(val login: String, val password: String, val captcha: String)