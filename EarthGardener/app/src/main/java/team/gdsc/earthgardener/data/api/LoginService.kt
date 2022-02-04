package team.gdsc.earthgardener.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData
import team.gdsc.earthgardener.data.model.response.ResCheckNicknameSuccessData
import team.gdsc.earthgardener.data.model.response.ResSignUpSuccessData

interface LoginService {
    // Get Email Code
    @GET("user/signup/email")
    suspend fun getEmail(
        @Query("email") email: String
    ):ResCheckEmailSuccessData

    // Get NickName
    @GET("user/signup/nickname")
    suspend fun getNickName(
        @Query("nickname") nickname: String
    ): ResCheckNicknameSuccessData

    // Post SignUp
    @Multipart
    @POST("user/signup")
    suspend fun postSignUp(
        @PartMap data: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): ResSignUpSuccessData

    // Post SignIn
}