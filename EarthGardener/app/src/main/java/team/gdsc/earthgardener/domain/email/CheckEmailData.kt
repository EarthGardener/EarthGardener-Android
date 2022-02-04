package team.gdsc.earthgardener.domain.email

data class CheckEmailData(
    val code: String,
    val message: String,
    val status: Int
)
