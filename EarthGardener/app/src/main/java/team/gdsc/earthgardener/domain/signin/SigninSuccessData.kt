package team.gdsc.earthgardener.domain.signin

data class SigninSuccessData(
    val message: String,
    val status: Int,
    val token: String
)
