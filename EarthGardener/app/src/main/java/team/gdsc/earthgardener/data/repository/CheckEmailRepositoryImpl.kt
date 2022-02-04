package team.gdsc.earthgardener.data.repository

import team.gdsc.earthgardener.data.datasource.CheckEmailDataSource
import team.gdsc.earthgardener.data.mapper.CheckEmailMapper
import team.gdsc.earthgardener.data.model.request.ReqCheckEmailSuccessData
import team.gdsc.earthgardener.domain.CheckEmailData
import team.gdsc.earthgardener.domain.LoginRepository

class CheckEmailRepositoryImpl(private val checkEmailDataSource: CheckEmailDataSource): LoginRepository{
    override suspend fun getCheckEmailResult(body: ReqCheckEmailSuccessData): CheckEmailData {
        return CheckEmailMapper.mapperToCheckEmailResultSuccessData(checkEmailDataSource.getCheckEmail(body))
    }
}