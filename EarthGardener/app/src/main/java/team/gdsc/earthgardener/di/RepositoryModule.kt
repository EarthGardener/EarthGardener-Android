package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.repository.CheckEmailRepositoryImpl
import team.gdsc.earthgardener.data.repository.CheckNicknameRepositoryImpl
import team.gdsc.earthgardener.data.repository.tree.TreeInfoRepositoryImpl
import team.gdsc.earthgardener.domain.email.CheckEmailRepository
import team.gdsc.earthgardener.domain.nickname.CheckNicknameRepository
import team.gdsc.earthgardener.domain.repository.tree.TreeInfoRepository

val repositoryModule = module {
    single<CheckEmailRepository> { CheckEmailRepositoryImpl(get()) }
    single<CheckNicknameRepository> { CheckNicknameRepositoryImpl(get()) }
    single<TreeInfoRepository> { TreeInfoRepositoryImpl(get()) }
}