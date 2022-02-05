package team.gdsc.earthgardener.data.datasource.tree

import team.gdsc.earthgardener.data.api.TreeService
import team.gdsc.earthgardener.data.model.request.ReqTreeNameSuccessData
import team.gdsc.earthgardener.data.model.response.tree.ResTreeNameSuccessData

class TreeNameRemoteDataSource(private val treeService: TreeService) : TreeNameDataSource {
    override suspend fun postTreeName(body: ReqTreeNameSuccessData): ResTreeNameSuccessData {
        return treeService.postTreeName(body)
    }
}