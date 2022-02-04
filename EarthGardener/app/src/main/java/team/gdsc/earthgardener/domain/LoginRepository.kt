package team.gdsc.earthgardener.domain

interface LoginRepository {
    suspend fun getCheckEmailResult(email: String): CheckEmailData
}