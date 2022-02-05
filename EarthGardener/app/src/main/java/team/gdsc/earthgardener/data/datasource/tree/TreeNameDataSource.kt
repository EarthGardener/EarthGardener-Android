package team.gdsc.earthgardener.data.datasource.tree

import team.gdsc.earthgardener.data.model.request.ReqTreeNameSuccessData
import team.gdsc.earthgardener.data.model.response.tree.ResTreeNameSuccessData

interface TreeNameDataSource {
    suspend fun postTreeName(body: ReqTreeNameSuccessData) : ResTreeNameSuccessData
}