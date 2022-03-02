package team.gdsc.earthgardener.data.datasource.profile.modifyprofile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.api.ProfileService
import team.gdsc.earthgardener.data.model.response.profile.ResModifyProfileSuccessData

class ModifyProfileRemoteDataSource(private val profileService: ProfileService)
    :ModifyProfileDataSource{
    override suspend fun putProfile(
        image: MultipartBody.Part,
        data: HashMap<String, RequestBody>
    ): ResModifyProfileSuccessData {
        return profileService.putProfile(image, data)
    }
}