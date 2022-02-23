package team.gdsc.earthgardener.presentation.mypage.modifyProfile.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import team.gdsc.earthgardener.data.model.response.profile.ResModifyProfileSuccessData

interface ModifyProfileRetrofitInterface {

    @Multipart
    @PUT("user/profile")
    fun putProfile(
        @Header("X-AUTH-TOKEN") token: String,
        @Part image: MultipartBody.Part,
        @PartMap data: HashMap<String, RequestBody>
    ): Call<ModifyProfileResponse>
}