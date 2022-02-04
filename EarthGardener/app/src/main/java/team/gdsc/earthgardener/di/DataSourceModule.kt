package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.datasource.email.CheckEmailDataSource
import team.gdsc.earthgardener.data.datasource.email.CheckEmailRemoteDataSource
import team.gdsc.earthgardener.data.datasource.nickname.CheckNicknameDataSource
import team.gdsc.earthgardener.data.datasource.nickname.CheckNicknameRemoteDataSource
import team.gdsc.earthgardener.data.datasource.tree.TreeInfoDataSource
import team.gdsc.earthgardener.data.datasource.tree.TreeInfoRemoteDataSource

val dataSourceModule = module {
    single<CheckEmailDataSource> { CheckEmailRemoteDataSource(get()) }
    single<CheckNicknameDataSource> { CheckNicknameRemoteDataSource(get()) }
    single<TreeInfoDataSource> { TreeInfoRemoteDataSource(get()) }
}