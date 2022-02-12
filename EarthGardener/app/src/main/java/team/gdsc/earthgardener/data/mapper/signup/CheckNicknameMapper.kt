package team.gdsc.earthgardener.data.mapper.signup

import team.gdsc.earthgardener.data.model.response.signup.ResCheckNicknameSuccessData
import team.gdsc.earthgardener.domain.nickname.CheckNicknameData

object CheckNicknameMapper {
    fun mapperToCheckNicknameResultSuccessData(resCheckNicknameSuccessData: ResCheckNicknameSuccessData):
            CheckNicknameData {
        return CheckNicknameData(
            message = resCheckNicknameSuccessData.message,
            status = resCheckNicknameSuccessData.status
        )
    }
}