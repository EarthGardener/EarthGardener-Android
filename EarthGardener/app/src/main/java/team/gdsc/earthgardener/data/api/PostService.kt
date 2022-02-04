package team.gdsc.earthgardener.data.api

import retrofit2.http.GET
import team.gdsc.earthgardener.data.model.response.ResChecklistData

interface PostService {

    @GET("posts/new/checklist")
    suspend fun getCheckList(
    ): ResChecklistData
}