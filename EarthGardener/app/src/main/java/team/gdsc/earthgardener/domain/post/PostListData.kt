package team.gdsc.earthgardener.domain.post

data class PostListData(
    val data: List<PostList>,
) {
    data class PostList(
        val title: String,
        val exp: Int,
        val date: Int,
        val checkList: List<String>
    )
}