package team.gdsc.earthgardener.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import team.gdsc.earthgardener.data.model.request.ReqCheckEmailSuccessData
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData

interface LoginService {
    // Get Email Code
    @GET("/user/signup/email")
    suspend fun GetEmail(
        @Body body: ReqCheckEmailSuccessData
    ):ResCheckEmailSuccessData

    // Get NickName


    // Post SignUp


    // Post SignIn
}