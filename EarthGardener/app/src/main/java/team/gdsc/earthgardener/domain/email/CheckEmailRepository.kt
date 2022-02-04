package team.gdsc.earthgardener.domain.email

interface CheckEmailRepository {
    suspend fun getCheckEmailResult(email: String): CheckEmailData
}