package team.gdsc.earthgardener.data.datasource.post

import team.gdsc.earthgardener.data.api.PostService
import team.gdsc.earthgardener.data.model.response.ResChecklistData
import team.gdsc.earthgardener.data.model.response.ResPostData

class PostRemoteDataSource(private val postService: PostService): PostDataSource {
    override suspend fun getCheckList(): ResChecklistData {
        return postService.getCheckList()
    }

    override suspend fun getPostList(date: String): ResPostData {
        return postService.getPostList(date)
    }
}