package team.gdsc.earthgardener.data.datasource.post

import team.gdsc.earthgardener.data.model.response.ResChecklistData
import team.gdsc.earthgardener.data.model.response.ResPostData
import team.gdsc.earthgardener.domain.post.CheckListData
import java.util.*

interface PostDataSource {
    suspend fun getCheckList(): ResChecklistData

    suspend fun getPostList(date: String): ResPostData
}