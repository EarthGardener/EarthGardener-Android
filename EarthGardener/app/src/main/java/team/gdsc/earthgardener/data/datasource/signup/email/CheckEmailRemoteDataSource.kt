package team.gdsc.earthgardener.data.datasource.signup.email

import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.response.signup.ResCheckEmailSuccessData

class CheckEmailRemoteDataSource (private val loginService: LoginService)
    : CheckEmailDataSource {
    override suspend fun getCheckEmail(email: String): ResCheckEmailSuccessData {
        return loginService.getEmail(email)
    }
}