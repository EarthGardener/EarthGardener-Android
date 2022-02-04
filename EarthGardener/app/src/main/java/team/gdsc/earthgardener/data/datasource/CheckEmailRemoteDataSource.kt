package team.gdsc.earthgardener.data.datasource

import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.request.ReqCheckEmailSuccessData
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData

class CheckEmailRemoteDataSource (private val loginService: LoginService) : CheckEmailDataSource {
    override suspend fun getCheckEmail(body: ReqCheckEmailSuccessData): ResCheckEmailSuccessData {
        return loginService.GetEmail(body)
    }
}