package team.gdsc.earthgardener.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.gdsc.earthgardener.presentation.main.viewmodel.MainViewModel
import team.gdsc.earthgardener.presentation.post.viewmodel.PostFormViewModel
import team.gdsc.earthgardener.presentation.user.signup.nickname.viewModel.CheckNicknameViewModel
import team.gdsc.earthgardener.presentation.user.signup.emailpw.viewModel.CheckEmailViewModel

val viewModelModule = module {
    viewModel { CheckEmailViewModel(get()) }
    viewModel { CheckNicknameViewModel(get()) }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { PostFormViewModel(get()) }
}