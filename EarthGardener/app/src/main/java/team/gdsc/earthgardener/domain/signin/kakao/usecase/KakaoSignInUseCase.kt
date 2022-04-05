package team.gdsc.earthgardener.domain.signin.kakao.usecase

import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.domain.signin.SigninSuccessData
import team.gdsc.earthgardener.domain.signin.kakao.KakaoSignInRepository

class KakaoSignInUseCase(private val kakaoSignInRepository: KakaoSignInRepository) {
    suspend operator fun invoke(data: ReqKakaoSignInSuccessData): SigninSuccessData{
        return kakaoSignInRepository.postKakaoSignInResult(data)
    }
}