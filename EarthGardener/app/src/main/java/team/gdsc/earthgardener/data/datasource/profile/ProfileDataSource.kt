package team.gdsc.earthgardener.data.datasource.profile

import team.gdsc.earthgardener.data.model.response.profile.ResProfileSuccessData

interface ProfileDataSource {
    suspend fun getProfile(): ResProfileSuccessData
}