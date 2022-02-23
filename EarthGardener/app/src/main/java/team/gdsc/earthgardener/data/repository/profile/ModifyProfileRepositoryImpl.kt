package team.gdsc.earthgardener.data.repository.profile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.datasource.profile.modify.ModifyProfileDataSource
import team.gdsc.earthgardener.data.mapper.profile.ModifyProfileMapper
import team.gdsc.earthgardener.domain.profile.modify.ModifyProfileData
import team.gdsc.earthgardener.domain.profile.modify.ModifyProfileRepository

class ModifyProfileRepositoryImpl(private val modifyProfileDataSource: ModifyProfileDataSource)
    :ModifyProfileRepository{
    override suspend fun putModifyProfileResult(
        data: HashMap<String, RequestBody>,
        image: MultipartBody.Part
    ): ModifyProfileData {
        return ModifyProfileMapper.mapperModifyProfileSuccessData(modifyProfileDataSource.putProfile(data, image))
    }

}