package team.gdsc.earthgardener.domain.profile.modifypassword

import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData

interface ModifyPasswordRepository {
    suspend fun putModifyPasswordResult(data:ReqModifyPasswordSuccessData)
    : ModifyPasswordData
}