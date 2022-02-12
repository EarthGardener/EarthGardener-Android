package team.gdsc.earthgardener.data.model.response.signin

data class ResSignInSuccessData(
    val message: String,
    val status: Int,
    val token: String
)