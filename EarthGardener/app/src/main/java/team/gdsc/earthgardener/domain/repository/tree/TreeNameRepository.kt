package team.gdsc.earthgardener.domain.repository.tree

import team.gdsc.earthgardener.data.model.request.ReqTreeNameSuccessData
import team.gdsc.earthgardener.domain.model.tree.TreeNameSuccessData

interface TreeNameRepository {
    suspend fun postTreeName(body: ReqTreeNameSuccessData) : TreeNameSuccessData
}