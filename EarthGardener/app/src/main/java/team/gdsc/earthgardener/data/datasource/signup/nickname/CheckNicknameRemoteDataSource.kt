package team.gdsc.earthgardener.data.datasource.signup.nickname

import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.response.signup.ResCheckNicknameSuccessData

class CheckNicknameRemoteDataSource(private val loginService: LoginService): CheckNicknameDataSource {
    override suspend fun getCheckNickname(nickname: String): ResCheckNicknameSuccessData {
        return loginService.getNickName(nickname)
    }

}