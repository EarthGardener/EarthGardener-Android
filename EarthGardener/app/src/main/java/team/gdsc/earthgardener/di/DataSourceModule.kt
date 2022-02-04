package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.datasource.post.PostDataSource
import team.gdsc.earthgardener.data.datasource.post.PostRemoteDataSource

val dataSourceModule = module {
    single<PostDataSource> { PostRemoteDataSource(get()) }
}