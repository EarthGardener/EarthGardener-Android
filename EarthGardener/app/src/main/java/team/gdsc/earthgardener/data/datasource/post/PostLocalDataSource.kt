package team.gdsc.earthgardener.data.datasource.post

import team.gdsc.earthgardener.data.api.PostService
import team.gdsc.earthgardener.data.model.response.ResChecklistData
import team.gdsc.earthgardener.data.model.response.ResPostData

class PostLocalDataSource(private val postService: PostService) : PostDataSource {
    override suspend fun getCheckList(): ResChecklistData {
        return ResChecklistData(
            message = "success",
            status = 1,
            data = (
                    listOf(
                        ResChecklistData.Data(
                            id = 1,
                            ment = "first todo..."
                        ),
                        ResChecklistData.Data(
                            id = 2,
                            ment = "second todo..."
                        ),
                        ResChecklistData.Data(
                            id = 3,
                            ment = "third todo..."
                        ),
                        ResChecklistData.Data(
                            id = 4,
                            ment = "forth todo..."
                        ),
                        ResChecklistData.Data(
                            id = 5,
                            ment = "fifth todo..."
                        )
                    )
                    )
        )
    }

    override suspend fun getPostList(date: String): ResPostData {
        return ResPostData(
            message = "postlist success",
            status = 1,
            data = (
                    listOf(
                        ResPostData.Data(
                            title = "안녕하세요",
                            exp = 200,
                            date = 31,
                            checklist = listOf("1.......", "2............", "3.........")
                        )
                    )
                    )
        )
    }
}