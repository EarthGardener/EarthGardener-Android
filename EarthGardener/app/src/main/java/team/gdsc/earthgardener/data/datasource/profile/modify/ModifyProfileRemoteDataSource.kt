package team.gdsc.earthgardener.data.datasource.profile.modify

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.api.ProfileService
import team.gdsc.earthgardener.data.model.response.profile.ResModifyProfileSuccessData

class ModifyProfileRemoteDataSource(private val profileService: ProfileService)
    :ModifyProfileDataSource{
    override suspend fun putProfile(
        data: HashMap<String, RequestBody>,
        image: MultipartBody.Part
    ): ResModifyProfileSuccessData {
        return profileService.putProfile(data, image)
    }

}