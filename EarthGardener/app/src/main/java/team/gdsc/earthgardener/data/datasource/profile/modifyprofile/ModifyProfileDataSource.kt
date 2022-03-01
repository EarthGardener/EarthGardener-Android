package team.gdsc.earthgardener.data.datasource.profile.modifyprofile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.model.response.profile.ResModifyProfileSuccessData

interface ModifyProfileDataSource {
    suspend fun putProfile(image: MultipartBody.Part,data: HashMap<String, RequestBody>): ResModifyProfileSuccessData
}