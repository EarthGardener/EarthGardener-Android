package team.gdsc.earthgardener.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.gdsc.earthgardener.presentation.post.viewmodel.PostFormViewModel

val viewModelModule = module {
    viewModel { PostFormViewModel(get()) }
}