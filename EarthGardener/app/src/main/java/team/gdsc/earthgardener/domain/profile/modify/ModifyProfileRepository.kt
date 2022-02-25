package team.gdsc.earthgardener.domain.profile.modify

import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ModifyProfileRepository {
    suspend fun putModifyProfileResult(image: MultipartBody.Part, data: HashMap<String, RequestBody>): ModifyProfileData
}