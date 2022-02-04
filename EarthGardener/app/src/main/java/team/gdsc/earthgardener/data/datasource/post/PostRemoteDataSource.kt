package team.gdsc.earthgardener.data.datasource.post

import team.gdsc.earthgardener.data.api.PostService
import team.gdsc.earthgardener.data.model.response.ResChecklistData

class PostRemoteDataSource(private val postService: PostService): PostDataSource {
    override suspend fun getCheckList(): ResChecklistData {
        return getCheckList()
    }
}