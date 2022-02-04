package team.gdsc.earthgardener.data.datasource.post

import team.gdsc.earthgardener.data.model.response.ResChecklistData
import team.gdsc.earthgardener.domain.post.CheckListData

interface PostDataSource {
    suspend fun getCheckList(): ResChecklistData
}