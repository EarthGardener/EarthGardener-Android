package team.gdsc.earthgardener.domain.profile

interface ProfileRepository {
    suspend fun getProfileResult(): ProfileData
}