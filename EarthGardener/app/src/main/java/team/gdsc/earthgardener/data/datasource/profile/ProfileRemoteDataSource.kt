package team.gdsc.earthgardener.data.datasource.profile

import team.gdsc.earthgardener.data.api.ProfileService
import team.gdsc.earthgardener.data.model.response.profile.ResProfileSuccessData

class ProfileRemoteDataSource(private val profileService: ProfileService)
    :ProfileDataSource{
    override suspend fun getProfile(): ResProfileSuccessData {
        return profileService.getProfile()
    }
}