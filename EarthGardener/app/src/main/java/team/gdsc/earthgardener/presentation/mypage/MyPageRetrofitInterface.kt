package team.gdsc.earthgardener.presentation.mypage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import team.gdsc.earthgardener.presentation.mypage.retrofit.ProfileResponse

interface MyPageRetrofitInterface {
    @GET("user/profile")
    fun getProfile(
        @Header("X-AUTH-TOKEN") token: String
    ): Call<ProfileResponse>
}