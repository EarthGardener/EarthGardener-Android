package team.gdsc.earthgardener.data.datasource.nickname

import team.gdsc.earthgardener.data.model.response.ResCheckNicknameSuccessData

interface CheckNicknameDataSource {
    suspend fun getCheckNickname(nickname: String): ResCheckNicknameSuccessData
}