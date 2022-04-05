package team.gdsc.earthgardener.data.repository.signin

import team.gdsc.earthgardener.data.datasource.signin.kakao.KakaoSignInDataSource
import team.gdsc.earthgardener.data.mapper.signin.KakaoSignInMapper
import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.domain.signin.SigninSuccessData
import team.gdsc.earthgardener.domain.signin.kakao.KakaoSignInRepository

class KakaoSignInRepositoryImpl(private val kakaoSignInDataSource: KakaoSignInDataSource)
    : KakaoSignInRepository {
    override suspend fun postKakaoSignInResult(data: ReqKakaoSignInSuccessData): SigninSuccessData {
        return KakaoSignInMapper.mapperKakaoSignInSuccessData(kakaoSignInDataSource.postKakaoSignIn(data))
    }

}