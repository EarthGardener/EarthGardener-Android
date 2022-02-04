package team.gdsc.earthgardener.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.gdsc.earthgardener.presentation.user.signup.nickname.viewModel.CheckNicknameViewModel
import team.gdsc.earthgardener.presentation.user.signup.emailpw.viewModel.CheckEmailViewModel

val viewModelModule = module{
    viewModel{ CheckEmailViewModel(get()) }
    viewModel{ CheckNicknameViewModel(get()) }
}