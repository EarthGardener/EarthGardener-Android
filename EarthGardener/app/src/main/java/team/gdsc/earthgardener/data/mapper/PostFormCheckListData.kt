package team.gdsc.earthgardener.data.mapper

import org.koin.core.component.getScopeId
import team.gdsc.earthgardener.data.model.response.ResChecklistData
import team.gdsc.earthgardener.domain.post.CheckListData

object PostFormCheckListData {

    fun mapperToCheckListData(resChecklistData: ResChecklistData) : CheckListData{
        return CheckListData(
            data = resChecklistData.data.map {
                CheckListData.CheckList(
                    id = it.id,
                    ment = it.ment
                )
            }
        )
    }
}