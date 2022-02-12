package team.gdsc.earthgardener.data.datasource.signup.nickname

import team.gdsc.earthgardener.data.model.response.signup.ResCheckNicknameSuccessData

interface CheckNicknameDataSource {
    suspend fun getCheckNickname(nickname: String): ResCheckNicknameSuccessData
}