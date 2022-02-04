package team.gdsc.earthgardener.domain

import team.gdsc.earthgardener.data.model.request.ReqCheckEmailSuccessData
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData

interface LoginRepository {
    suspend fun getCheckEmailResult(body: ReqCheckEmailSuccessData): CheckEmailData
}