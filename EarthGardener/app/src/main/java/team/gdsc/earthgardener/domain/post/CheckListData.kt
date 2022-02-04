package team.gdsc.earthgardener.domain.post

data class CheckListData(
    val data: List<CheckList>
) {
    data class CheckList(
        val id: Int,
        val ment: String
    )
}
