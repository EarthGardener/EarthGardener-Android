package team.gdsc.earthgardener.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.datasource.signup.SignUpDataSource
import team.gdsc.earthgardener.data.mapper.SignUpMapper
import team.gdsc.earthgardener.domain.signup.SignUpData
import team.gdsc.earthgardener.domain.signup.SignUpRepository

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource)
    :SignUpRepository{
    override suspend fun postSignUpRepositoryResult(
        data: HashMap<String, RequestBody>,
        image: MultipartBody.Part
    ): SignUpData {
        return SignUpMapper.mapperToSignUpResultSuccessData(signUpDataSource.postSignUp(data, image))
    }
}