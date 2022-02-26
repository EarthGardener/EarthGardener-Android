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
        image: MultipartBody.Part,
        data: HashMap<String, RequestBody>
    ): ModifyProfileData {
        return ModifyProfileMapper.mapperModifyProfileSuccessData(modifyProfileDataSource.putProfile(image, data))
    }

}