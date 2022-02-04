package team.gdsc.earthgardener.data.mapper

import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData
import team.gdsc.earthgardener.domain.CheckEmailData

object CheckEmailMapper {
    fun mapperToCheckEmailResultSuccessData(resCheckEmailSuccessData: ResCheckEmailSuccessData): CheckEmailData{
        return CheckEmailData(
            code = resCheckEmailSuccessData.code
        )
    }
}