package team.gdsc.earthgardener.data.mapper

import team.gdsc.earthgardener.data.model.response.ResCheckNicknameSuccessData
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