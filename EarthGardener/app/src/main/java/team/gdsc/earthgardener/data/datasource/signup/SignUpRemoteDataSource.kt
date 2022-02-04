package team.gdsc.earthgardener.data.datasource.signup

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.api.LoginService
import team.gdsc.earthgardener.data.model.response.ResSignUpSuccessData

class SignUpRemoteDataSource(private val loginService: LoginService)
    :SignUpDataSource{
    override suspend fun postSignUp(
        data: HashMap<String, RequestBody>,
        image: MultipartBody.Part
    ): ResSignUpSuccessData {
        return loginService.postSignUp(data, image)
    }

}