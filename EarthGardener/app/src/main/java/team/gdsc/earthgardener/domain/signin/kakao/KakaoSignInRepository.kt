package team.gdsc.earthgardener.domain.signin.kakao

import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.domain.signin.SigninSuccessData

interface KakaoSignInRepository {
    suspend fun postKakaoSignInResult(data: ReqKakaoSignInSuccessData): SigninSuccessData
}