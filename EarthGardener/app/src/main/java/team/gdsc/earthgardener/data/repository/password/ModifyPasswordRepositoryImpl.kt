package team.gdsc.earthgardener.data.repository.password

import team.gdsc.earthgardener.data.datasource.profile.modifypassword.ModifyPasswordDataSource
import team.gdsc.earthgardener.data.mapper.profile.ModifyPasswordMapper
import team.gdsc.earthgardener.data.model.request.password.ReqModifyPasswordSuccessData
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordData
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordRepository

class ModifyPasswordRepositoryImpl(private val modifyPasswordDataSource: ModifyPasswordDataSource)
    :ModifyPasswordRepository{
    override suspend fun putModifyPasswordResult(data: ReqModifyPasswordSuccessData): ModifyPasswordData {
        return ModifyPasswordMapper.mapperModifyPasswordSuccessData(modifyPasswordDataSource.putPassword(data))
    }
}