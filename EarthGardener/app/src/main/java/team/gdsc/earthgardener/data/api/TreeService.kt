package team.gdsc.earthgardener.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import team.gdsc.earthgardener.data.model.request.ReqTreeNameSuccessData
import team.gdsc.earthgardener.data.model.response.tree.ResTreeInfoSuccessData
import team.gdsc.earthgardener.data.model.response.tree.ResTreeNameSuccessData

interface TreeService {

    @GET("tree/info")
    suspend fun getTreeInfo(): ResTreeInfoSuccessData

    @POST("tree/name")
    suspend fun postTreeName(
        @Body body: ReqTreeNameSuccessData
    ): ResTreeNameSuccessData
}