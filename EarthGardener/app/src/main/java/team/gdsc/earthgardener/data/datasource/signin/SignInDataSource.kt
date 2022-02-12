package team.gdsc.earthgardener.data.datasource.signin

import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.data.model.response.signin.ResSignInSuccessData

interface SignInDataSource {
    suspend fun postSignIn(data: ReqSignInSuccessData): ResSignInSuccessData
}