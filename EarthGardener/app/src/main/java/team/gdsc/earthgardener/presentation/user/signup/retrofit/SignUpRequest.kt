package team.gdsc.earthgardener.presentation.user.signup.retrofit

data class SignUpRequest(
    val email: String,
    val pw: String,
    val nickname: String
)
