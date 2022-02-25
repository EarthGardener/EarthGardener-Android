package team.gdsc.earthgardener.data.mapper.profile

import team.gdsc.earthgardener.data.model.response.profile.ResModifyProfileSuccessData
import team.gdsc.earthgardener.domain.profile.modify.ModifyProfileData

object ModifyProfileMapper {
    fun mapperModifyProfileSuccessData(resModifyProfileSuccessData: ResModifyProfileSuccessData)
    : ModifyProfileData{
        return ModifyProfileData(
            message = resModifyProfileSuccessData.message,
            status = resModifyProfileSuccessData.status
        )
    }
}