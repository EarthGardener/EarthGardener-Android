package team.gdsc.earthgardener.data.mapper

import team.gdsc.earthgardener.data.model.response.tree.ResTreeInfoSuccessData
import team.gdsc.earthgardener.domain.model.tree.TreeInfoSuccessData

object TreeInfoMapper {

    fun mapperToTreeInfoSuccessData(resTreeInfoSuccessData: ResTreeInfoSuccessData) : TreeInfoSuccessData {
        return TreeInfoSuccessData(
            data = TreeInfoSuccessData.TreeInfo(
                resTreeInfoSuccessData.data.name,
                resTreeInfoSuccessData.data.level,
                resTreeInfoSuccessData.data.exp,
                resTreeInfoSuccessData.data.totalSum,
                resTreeInfoSuccessData.data.monthSum
            ),
            resTreeInfoSuccessData.isLevelUp,
            resTreeInfoSuccessData.userNickName
        )
    }
}