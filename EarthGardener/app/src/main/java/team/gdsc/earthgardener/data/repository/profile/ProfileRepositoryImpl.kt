package team.gdsc.earthgardener.data.repository.profile

import team.gdsc.earthgardener.data.datasource.profile.ProfileDataSource
import team.gdsc.earthgardener.data.mapper.profile.ProfileMapper
import team.gdsc.earthgardener.domain.profile.ProfileData
import team.gdsc.earthgardener.domain.profile.ProfileRepository

class ProfileRepositoryImpl(private val profileDataSource: ProfileDataSource)
    : ProfileRepository {
    override suspend fun getProfileResult(): ProfileData {
        return ProfileMapper.mapperProfileSuccessData(profileDataSource.getProfile())
    }
}