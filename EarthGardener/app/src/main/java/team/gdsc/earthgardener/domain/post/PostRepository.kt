package team.gdsc.earthgardener.domain.post

interface PostRepository {
    suspend fun getCheckList() : CheckListData
}