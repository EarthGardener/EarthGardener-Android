package team.gdsc.earthgardener.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import team.gdsc.earthgardener.data.model.response.profile.ResModifyProfileSuccessData
import team.gdsc.earthgardener.data.model.response.profile.ResProfileSuccessData

interface ProfileService {
    @GET("user/profile")
    suspend fun getProfile() : ResProfileSuccessData

    @Multipart
    @PUT("user/profile")
    suspend fun putProfile(
        @Part image: MultipartBody.Part,
        @PartMap data: HashMap<String, RequestBody>
    ): ResModifyProfileSuccessData
}