package team.gdsc.earthgardener.data.mapper.profile

import team.gdsc.earthgardener.data.model.response.password.ResModifyPasswordSuccessData
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordData

object ModifyPasswordMapper {
    fun mapperModifyPasswordSuccessData(resModifyPasswordSuccessData: ResModifyPasswordSuccessData)
    : ModifyPasswordData {
        return ModifyPasswordData(
            message = resModifyPasswordSuccessData.message,
            status = resModifyPasswordSuccessData.status
        )
    }
}