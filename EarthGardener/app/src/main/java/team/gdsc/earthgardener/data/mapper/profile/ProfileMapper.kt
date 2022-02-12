package team.gdsc.earthgardener.data.mapper.profile

import team.gdsc.earthgardener.data.model.response.profile.ResProfileSuccessData
import team.gdsc.earthgardener.domain.profile.ProfileData

object ProfileMapper {
    fun mapperProfileSuccessData(resProfileSuccessData: ResProfileSuccessData)
    : ProfileData {
        return ProfileData(
            message = resProfileSuccessData.message,
            status = resProfileSuccessData.status,
            data = ProfileData.ProfileInfo(
                resProfileSuccessData.data.email,
                resProfileSuccessData.data.nickname,
                resProfileSuccessData.data.image_url
            )
        )
    }
}