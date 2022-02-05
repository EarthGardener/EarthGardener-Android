package team.gdsc.earthgardener.data.mapper

import team.gdsc.earthgardener.data.model.response.ResPostData
import team.gdsc.earthgardener.domain.post.PostListData

object PostListsData {

    fun mapperToPostListData(resPostData: ResPostData): PostListData {

        return PostListData(
            data = resPostData.data.map {
                PostListData.PostList(
                    date = it?.date!!,
                    exp = it.exp,
                    title = it.title,
                    checkList = it.checklist
                )
            }
        )
    }
}
