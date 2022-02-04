package team.gdsc.earthgardener.domain.nickname

interface CheckNicknameRepository {
    suspend fun getCheckNicknameResult(nickname: String): CheckNicknameData
}