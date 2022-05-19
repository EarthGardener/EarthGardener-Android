package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.domain.profile.modifypassword.usecase.ModifyPasswordUseCase
import team.gdsc.earthgardener.domain.profile.modifyprofile.usecase.ModifyProfileUseCase
import team.gdsc.earthgardener.domain.signin.usecase.SignInUseCase

val useCaseModule = module{
    single { SignInUseCase(get())}
    single { ModifyPasswordUseCase(get()) }
    single { ModifyProfileUseCase(get()) }
}