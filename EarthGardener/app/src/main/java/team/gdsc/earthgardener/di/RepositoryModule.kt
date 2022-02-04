package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.repository.CheckEmailRepositoryImpl
import team.gdsc.earthgardener.domain.LoginRepository

val repositoryModule = module{
    single<LoginRepository>{CheckEmailRepositoryImpl(get())}
}