package team.gdsc.earthgardener.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.gdsc.earthgardener.presentation.main.viewmodel.MainViewModel
import team.gdsc.earthgardener.presentation.mypage.modifyPW.viewmodel.ModifyPasswordViewModel
import team.gdsc.earthgardener.presentation.mypage.modifyProfile.viewmodel.ModifyProfileViewModel
import team.gdsc.earthgardener.presentation.post.viewmodel.PostFormViewModel
import team.gdsc.earthgardener.presentation.user.kakaologin.viewModel.KakaoSignUpViewModel
import team.gdsc.earthgardener.presentation.user.login.viewModel.SignInViewModel
import team.gdsc.earthgardener.presentation.user.signup.viewModel.SignUpViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get(), get()) }
    viewModel { PostFormViewModel(get()) }
    viewModel { SignInViewModel(get(), get())}
    viewModel { SignUpViewModel(get(), get(), get()) }
    viewModel { ModifyProfileViewModel(get()) }
    viewModel { ModifyPasswordViewModel(get())}
    viewModel { KakaoSignUpViewModel(get())}
}