package team.gdsc.earthgardener.data.datasource.email

import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData

class CheckEmailRemoteDataSource (private val loginService: LoginService) : CheckEmailDataSource {
    override suspend fun getCheckEmail(email: String): ResCheckEmailSuccessData {
        return loginService.getEmail(email)
    }
}