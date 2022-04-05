package team.gdsc.earthgardener.data.datasource.signin.kakao

import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.data.model.response.signin.ResSignInSuccessData

interface KakaoSignInDataSource {
    suspend fun postKakaoSignIn(data: ReqKakaoSignInSuccessData): ResSignInSuccessData
}