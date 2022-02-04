package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.repository.PostRepositoryImpl
import team.gdsc.earthgardener.domain.post.PostRepository

val repositoryModule = module {

    single<PostRepository> {
        PostRepositoryImpl(get())
    }
}
