package team.gdsc.earthgardener.data.repository.signin

import team.gdsc.earthgardener.data.datasource.signin.SignInDataSource
import team.gdsc.earthgardener.data.mapper.signin.SignInMapper
import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.domain.signin.SignInRepository
import team.gdsc.earthgardener.domain.signin.SigninSuccessData

class SignInRepositoryImpl(private val signInDataSource: SignInDataSource)
    :SignInRepository{
    override suspend fun postSignInResult(data: ReqSignInSuccessData): SigninSuccessData {
        return SignInMapper.mapperSignInSuccessData(signInDataSource.postSignIn(data))
    }
}