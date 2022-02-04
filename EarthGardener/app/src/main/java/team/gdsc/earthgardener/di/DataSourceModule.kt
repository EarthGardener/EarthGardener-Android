package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.datasource.CheckEmailDataSource
import team.gdsc.earthgardener.data.datasource.CheckEmailRemoteDataSource

val dataSourceModule = module{
    single<CheckEmailDataSource>{CheckEmailRemoteDataSource(get())}
}