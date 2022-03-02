package team.gdsc.earthgardener.data.datasource.profile.modifypassword

import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData
import team.gdsc.earthgardener.data.model.response.password.ResModifyPasswordSuccessData

interface ModifyPasswordDataSource {
    suspend fun putPassword(data: ReqModifyPasswordSuccessData): ResModifyPasswordSuccessData
}