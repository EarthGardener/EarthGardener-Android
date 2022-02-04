package team.gdsc.earthgardener.data.repository

import team.gdsc.earthgardener.data.datasource.post.PostDataSource
import team.gdsc.earthgardener.data.mapper.PostFormCheckListData
import team.gdsc.earthgardener.data.model.response.ResChecklistData
import team.gdsc.earthgardener.domain.post.CheckListData
import team.gdsc.earthgardener.domain.post.PostRepository

class PostRepositoryImpl(private val postDataSource: PostDataSource) : PostRepository{

    override suspend fun getCheckList(): CheckListData {
        return PostFormCheckListData.mapperToCheckListData(postDataSource.getCheckList())
    }
}