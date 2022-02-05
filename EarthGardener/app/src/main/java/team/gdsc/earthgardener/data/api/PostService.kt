package team.gdsc.earthgardener.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import team.gdsc.earthgardener.data.model.response.ResChecklistData
import team.gdsc.earthgardener.data.model.response.ResPostData
import java.util.*

interface PostService {

    @GET("posts/new/checklist")
    suspend fun getCheckList(
    ): ResChecklistData

    @GET("posts")
    suspend fun getPostList(
        @Query("date") date: String
    ): ResPostData
}