package team.gdsc.earthgardener.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData
import team.gdsc.earthgardener.data.model.response.ResCheckNicknameSuccessData

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


    // Post SignIn
}