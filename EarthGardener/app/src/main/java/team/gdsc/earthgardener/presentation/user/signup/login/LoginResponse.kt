package team.gdsc.earthgardener.presentation.user.signup.login

data class LoginResponse(
    val message: String,
    val status: Int,
    val token: String
)