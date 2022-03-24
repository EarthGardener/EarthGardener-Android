package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.domain.profile.modifypassword.usecase.ModifyPwUseCase
import team.gdsc.earthgardener.domain.signin.usecase.SignInUseCase

val useCaseModule = module{
    single { SignInUseCase(get())}
    single { ModifyPwUseCase(get())}
}