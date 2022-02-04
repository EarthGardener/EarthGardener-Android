package team.gdsc.earthgardener.data.repository

import team.gdsc.earthgardener.data.datasource.email.CheckEmailDataSource
import team.gdsc.earthgardener.data.mapper.CheckEmailMapper
import team.gdsc.earthgardener.domain.email.CheckEmailData
import team.gdsc.earthgardener.domain.email.CheckEmailRepository

class CheckEmailRepositoryImpl(private val checkEmailDataSource: CheckEmailDataSource):
    CheckEmailRepository {
    override suspend fun getCheckEmailResult(email: String): CheckEmailData {
        return CheckEmailMapper.mapperToCheckEmailResultSuccessData(checkEmailDataSource.getCheckEmail(email))
    }
}