package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.datasource.tree.TreeInfoDataSource
import team.gdsc.earthgardener.data.datasource.tree.TreeInfoRemoteDataSource

val dataSourceModule = module {

    single<TreeInfoDataSource> { TreeInfoRemoteDataSource(get())}
}