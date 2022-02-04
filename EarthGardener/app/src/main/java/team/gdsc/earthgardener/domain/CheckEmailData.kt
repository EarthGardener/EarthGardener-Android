package team.gdsc.earthgardener.domain

data class CheckEmailData(
    val code: String,
    val message: String,
    val status: Int
)
