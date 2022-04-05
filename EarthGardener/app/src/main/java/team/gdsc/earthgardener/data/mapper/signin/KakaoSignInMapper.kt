package team.gdsc.earthgardener.data.mapper.signin

import team.gdsc.earthgardener.data.model.response.signin.ResSignInSuccessData
import team.gdsc.earthgardener.domain.signin.SigninSuccessData

object KakaoSignInMapper {
    fun mapperKakaoSignInSuccessData(resSignInSuccessData: ResSignInSuccessData)
    : SigninSuccessData{
        return SigninSuccessData(
            message = resSignInSuccessData.message,
            status = resSignInSuccessData.status,
            token = resSignInSuccessData.token
        )
    }
}