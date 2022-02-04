package team.gdsc.earthgardener.domain.signup

import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SignUpRepository {
    suspend fun postSignUpRepositoryResult(
        data: HashMap<String, RequestBody>,
        image: MultipartBody.Part
    ): SignUpData
}