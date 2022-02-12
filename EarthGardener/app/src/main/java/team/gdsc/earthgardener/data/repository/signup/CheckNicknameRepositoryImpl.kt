package team.gdsc.earthgardener.data.repository.signup

import team.gdsc.earthgardener.data.datasource.signup.nickname.CheckNicknameDataSource
import team.gdsc.earthgardener.data.mapper.signup.CheckNicknameMapper
import team.gdsc.earthgardener.domain.nickname.CheckNicknameData
import team.gdsc.earthgardener.domain.nickname.CheckNicknameRepository

class CheckNicknameRepositoryImpl(private val checkNicknameDataSource: CheckNicknameDataSource)
    :CheckNicknameRepository{
    override suspend fun getCheckNicknameResult(nickname: String): CheckNicknameData {
        return CheckNicknameMapper.mapperToCheckNicknameResultSuccessData(checkNicknameDataSource.getCheckNickname(nickname))
    }
}