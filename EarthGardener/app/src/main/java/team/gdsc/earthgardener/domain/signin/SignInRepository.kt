package team.gdsc.earthgardener.domain.signin

import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData

interface SignInRepository {
    suspend fun postSignInResult(data: ReqSignInSuccessData): SigninSuccessData
}