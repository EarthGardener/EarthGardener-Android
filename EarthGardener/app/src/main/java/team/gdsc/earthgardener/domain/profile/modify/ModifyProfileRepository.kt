package team.gdsc.earthgardener.domain.profile.modify

import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ModifyProfileRepository {
    suspend fun putModifyProfileResult(data: HashMap<String, RequestBody>, image: MultipartBody.Part): ModifyProfileData
}