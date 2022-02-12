package team.gdsc.earthgardener.data.datasource.signin

import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.data.model.response.signin.ResSignInSuccessData

class SignInRemoteDataSource(private val loginService: LoginService)
    : SignInDataSource{
    override suspend fun postSignIn(data: ReqSignInSuccessData): ResSignInSuccessData {
        return loginService.postSignIn(data)
    }

}
