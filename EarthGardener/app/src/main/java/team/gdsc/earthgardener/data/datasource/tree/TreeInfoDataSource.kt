package team.gdsc.earthgardener.data.datasource.tree

import team.gdsc.earthgardener.data.model.response.tree.ResTreeInfoSuccessData

interface TreeInfoDataSource {

    suspend fun getTreeInfo() : ResTreeInfoSuccessData
}