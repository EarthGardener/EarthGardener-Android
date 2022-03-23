package team.gdsc.earthgardener.domain.signin.usecase

import team.gdsc.earthgardener.data.model.request.signin.ReqSignInSuccessData
import team.gdsc.earthgardener.domain.signin.SignInRepository
import team.gdsc.earthgardener.domain.signin.SigninSuccessData

class SignInUseCase(private val signInRepository: SignInRepository) {
    suspend operator fun invoke(data: ReqSignInSuccessData) : SigninSuccessData{
        return signInRepository.postSignInResult(data)
    }
}