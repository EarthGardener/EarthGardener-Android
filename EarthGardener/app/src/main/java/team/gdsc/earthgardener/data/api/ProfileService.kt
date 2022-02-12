package team.gdsc.earthgardener.data.api

import retrofit2.http.GET
import team.gdsc.earthgardener.data.model.response.profile.ResProfileSuccessData

interface ProfileService {
    @GET("user/profile")
    suspend fun getProfile() : ResProfileSuccessData
}