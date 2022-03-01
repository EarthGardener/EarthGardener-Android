package team.gdsc.earthgardener.data.repository.profile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.datasource.profile.modifyprofile.ModifyProfileDataSource
import team.gdsc.earthgardener.data.mapper.profile.ModifyProfileMapper
import team.gdsc.earthgardener.domain.profile.modifyprofile.ModifyProfileData
import team.gdsc.earthgardener.domain.profile.modifyprofile.ModifyProfileRepository

class ModifyProfileRepositoryImpl(private val modifyProfileDataSource: ModifyProfileDataSource)
    :ModifyProfileRepository{
    override suspend fun putModifyProfileResult(
        image: MultipartBody.Part,
        data: HashMap<String, RequestBody>
    ): ModifyProfileData {
        return ModifyProfileMapper.mapperModifyProfileSuccessData(modifyProfileDataSource.putProfile(image, data))
    }

}