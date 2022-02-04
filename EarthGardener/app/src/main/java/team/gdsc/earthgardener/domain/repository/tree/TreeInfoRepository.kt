package team.gdsc.earthgardener.domain.repository.tree

import team.gdsc.earthgardener.domain.model.tree.TreeInfoSuccessData

interface TreeInfoRepository {
    suspend fun getTreeInfoResult() : TreeInfoSuccessData
}