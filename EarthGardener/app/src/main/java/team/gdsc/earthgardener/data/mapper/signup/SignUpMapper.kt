package team.gdsc.earthgardener.data.mapper.signup

import team.gdsc.earthgardener.data.model.response.signup.ResSignUpSuccessData
import team.gdsc.earthgardener.domain.signup.SignUpData

object SignUpMapper {
    fun mapperToSignUpResultSuccessData(resSignUpSuccessData: ResSignUpSuccessData)
    : SignUpData{
        return SignUpData(
            message = resSignUpSuccessData.message,
            status = resSignUpSuccessData.status
        )
    }
}