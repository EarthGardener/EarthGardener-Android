package team.gdsc.earthgardener.data.datasource.signup

import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.gdsc.earthgardener.data.model.response.ResSignUpSuccessData

interface SignUpDataSource {
    suspend fun postSignUp(
        data: HashMap<String, RequestBody>,
        image: MultipartBody.Part
    ): ResSignUpSuccessData
}