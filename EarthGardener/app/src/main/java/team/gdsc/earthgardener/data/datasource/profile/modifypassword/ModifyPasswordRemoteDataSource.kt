package team.gdsc.earthgardener.data.datasource.profile.modifypassword

import team.gdsc.earthgardener.data.api.ProfileService
import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData
import team.gdsc.earthgardener.data.model.response.password.ResModifyPasswordSuccessData

class ModifyPasswordRemoteDataSource(private val profileService: ProfileService)
    : ModifyPasswordDataSource{
    override suspend fun putPassword(data: ReqModifyPasswordSuccessData): ResModifyPasswordSuccessData {
        return profileService.putPassword(data)
    }

}