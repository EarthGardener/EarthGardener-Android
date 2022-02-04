package team.gdsc.earthgardener.data.datasource.tree

import team.gdsc.earthgardener.data.api.TreeService
import team.gdsc.earthgardener.data.model.response.tree.ResTreeInfoSuccessData

class TreeInfoRemoteDataSource(private val treeService: TreeService) : TreeInfoDataSource {
    override suspend fun getTreeInfo(): ResTreeInfoSuccessData {
        return treeService.getTreeInfo()
    }
}