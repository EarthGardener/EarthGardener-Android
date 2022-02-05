package team.gdsc.earthgardener.presentation.user.signup.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import javax.security.auth.callback.Callback

interface LoginRetrofitInterface {
    @POST("user/signin")
    fun postLogin(
        @Body data: LoginRequest
    ): Call<LoginResponse>
}