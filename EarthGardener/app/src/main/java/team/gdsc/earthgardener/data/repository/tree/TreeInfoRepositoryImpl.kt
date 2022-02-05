package team.gdsc.earthgardener.data.repository.tree

import team.gdsc.earthgardener.data.datasource.tree.TreeInfoDataSource
import team.gdsc.earthgardener.data.mapper.TreeMapper
import team.gdsc.earthgardener.domain.model.tree.TreeInfoSuccessData
import team.gdsc.earthgardener.domain.repository.tree.TreeInfoRepository

class TreeInfoRepositoryImpl(private val treeInfoDataSource: TreeInfoDataSource) : TreeInfoRepository {
    override suspend fun getTreeInfoResult(): TreeInfoSuccessData {
        return TreeMapper.mapperToTreeInfoSuccessData(treeInfoDataSource.getTreeInfo())
    }
}