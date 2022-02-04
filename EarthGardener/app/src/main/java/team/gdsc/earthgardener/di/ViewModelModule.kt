package team.gdsc.earthgardener.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.gdsc.earthgardener.presentation.home.viewmodel.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}