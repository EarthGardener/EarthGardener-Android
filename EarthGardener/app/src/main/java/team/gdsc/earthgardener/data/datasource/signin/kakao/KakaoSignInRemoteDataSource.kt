package team.gdsc.earthgardener.data.datasource.signin.kakao

import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.data.model.response.signin.ResSignInSuccessData

class KakaoSignInRemoteDataSource(private val loginService: LoginService)
    : KakaoSignInDataSource{
    override suspend fun postKakaoSignIn(data: ReqKakaoSignInSuccessData): ResSignInSuccessData {
        return loginService.postKakaoSignIn(data)
    }
}