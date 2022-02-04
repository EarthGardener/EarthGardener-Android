package team.gdsc.earthgardener.data.repository

import team.gdsc.earthgardener.data.datasource.nickname.CheckNicknameDataSource
import team.gdsc.earthgardener.data.mapper.CheckNicknameMapper
import team.gdsc.earthgardener.domain.nickname.CheckNicknameData
import team.gdsc.earthgardener.domain.nickname.CheckNicknameRepository

class CheckNicknameRepositoryImpl(private val checkNicknameDataSource: CheckNicknameDataSource)
    :CheckNicknameRepository{
    override suspend fun getCheckNicknameResult(nickname: String): CheckNicknameData {
        return CheckNicknameMapper.mapperToCheckNicknameResultSuccessData(checkNicknameDataSource.getCheckNickname(nickname))
    }
}