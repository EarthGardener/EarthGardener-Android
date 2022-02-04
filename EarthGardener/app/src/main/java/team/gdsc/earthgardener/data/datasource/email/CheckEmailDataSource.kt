package team.gdsc.earthgardener.data.datasource.email

import team.gdsc.earthgardener.data.model.response.ResCheckEmailSuccessData

interface CheckEmailDataSource {
    suspend fun getCheckEmail(email: String): ResCheckEmailSuccessData
}