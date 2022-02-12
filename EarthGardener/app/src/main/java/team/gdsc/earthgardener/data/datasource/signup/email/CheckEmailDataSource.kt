package team.gdsc.earthgardener.data.datasource.signup.email

import team.gdsc.earthgardener.data.model.response.signup.ResCheckEmailSuccessData

interface CheckEmailDataSource {
    suspend fun getCheckEmail(email: String): ResCheckEmailSuccessData
}