package team.gdsc.earthgardener.data.datasource.nickname

import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.response.ResCheckNicknameSuccessData

class CheckNicknameRemoteDataSource(private val loginService: LoginService): CheckNicknameDataSource {
    override suspend fun getCheckNickname(nickname: String): ResCheckNicknameSuccessData {
        return loginService.getNickName(nickname)
    }

}