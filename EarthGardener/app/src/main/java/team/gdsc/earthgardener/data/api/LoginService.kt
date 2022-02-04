package team.gdsc.earthgardener.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData

interface LoginService {
    // Get Email Code
    @GET("user/signup/email")
    suspend fun GetEmail(
        @Query("email") email: String
    ):ResCheckEmailSuccessData

    // Get NickName


    // Post SignUp


    // Post SignIn
}