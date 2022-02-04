package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.repository.tree.TreeInfoRepositoryImpl
import team.gdsc.earthgardener.domain.repository.tree.TreeInfoRepository

val repositoryModule = module {
    single<TreeInfoRepository> { TreeInfoRepositoryImpl(get())}
}