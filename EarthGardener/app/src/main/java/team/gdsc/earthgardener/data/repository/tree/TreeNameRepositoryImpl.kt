package team.gdsc.earthgardener.data.repository.tree

import team.gdsc.earthgardener.data.datasource.tree.TreeNameDataSource
import team.gdsc.earthgardener.data.mapper.TreeMapper
import team.gdsc.earthgardener.data.model.request.ReqTreeNameSuccessData
import team.gdsc.earthgardener.domain.model.tree.TreeNameSuccessData
import team.gdsc.earthgardener.domain.repository.tree.TreeNameRepository

class TreeNameRepositoryImpl(private val treeNameDataSource: TreeNameDataSource) : TreeNameRepository {
    override suspend fun postTreeName(body: ReqTreeNameSuccessData): TreeNameSuccessData {
        return TreeMapper.mapperToTreeNameSuccessData(treeNameDataSource.postTreeName(body))
    }
}