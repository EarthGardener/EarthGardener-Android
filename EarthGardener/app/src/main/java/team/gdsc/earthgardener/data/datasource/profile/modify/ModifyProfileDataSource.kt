package team.gdsc.earthgardener.data.datasource.profile.modify

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.model.response.profile.ResModifyProfileSuccessData

interface ModifyProfileDataSource {
    suspend fun putProfile(data: HashMap<String, RequestBody>, image: MultipartBody.Part): ResModifyProfileSuccessData
}