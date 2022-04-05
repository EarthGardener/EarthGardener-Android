package team.gdsc.earthgardener.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import team.gdsc.earthgardener.data.model.request.signin.ReqKakaoSignInSuccessData
import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.data.model.response.signup.ResCheckEmailSuccessData
import team.gdsc.earthgardener.data.model.response.signup.ResCheckNicknameSuccessData
import team.gdsc.earthgardener.data.model.response.signin.ResSignInSuccessData
import team.gdsc.earthgardener.data.model.response.signup.ResSignUpSuccessData

interface LoginService {
    // Get Email Code
    @GET("user/signup/email")
    suspend fun getEmail(
        @Query("email") email: String
    ): ResCheckEmailSuccessData

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
    @POST("user/signin")
    suspend fun postSignIn(
        @Body data: ReqSignInSuccessData
    ): ResSignInSuccessData

    // Post KakaoSignIn
    @POST("/user/signin/kakao")
    suspend fun postKakaoSignIn(
        @Body data: ReqKakaoSignInSuccessData
    ): ResSignInSuccessData
}