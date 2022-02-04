package team.gdsc.earthgardener.data.datasource

import team.gdsc.earthgardener.data.model.request.ReqCheckEmailSuccessData
import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData

interface CheckEmailDataSource {
    suspend fun getCheckEmail(body: ReqCheckEmailSuccessData): ResCheckEmailSuccessData
}