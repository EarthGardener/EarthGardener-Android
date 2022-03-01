package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.repository.signup.CheckEmailRepositoryImpl
import team.gdsc.earthgardener.data.repository.signup.CheckNicknameRepositoryImpl
import team.gdsc.earthgardener.data.repository.tree.TreeInfoRepositoryImpl
import team.gdsc.earthgardener.data.repository.tree.TreeNameRepositoryImpl
import team.gdsc.earthgardener.data.repository.PostRepositoryImpl
import team.gdsc.earthgardener.data.repository.profile.ModifyProfileRepositoryImpl
import team.gdsc.earthgardener.data.repository.profile.ProfileRepositoryImpl
import team.gdsc.earthgardener.data.repository.signin.SignInRepositoryImpl
import team.gdsc.earthgardener.data.repository.signup.SignUpRepositoryImpl
import team.gdsc.earthgardener.domain.email.CheckEmailRepository
import team.gdsc.earthgardener.domain.nickname.CheckNicknameRepository
import team.gdsc.earthgardener.domain.repository.tree.TreeInfoRepository
import team.gdsc.earthgardener.domain.repository.tree.TreeNameRepository
import team.gdsc.earthgardener.domain.post.PostRepository
import team.gdsc.earthgardener.domain.profile.ProfileRepository
import team.gdsc.earthgardener.domain.profile.modify.ModifyProfileRepository
import team.gdsc.earthgardener.domain.signin.SignInRepository
import team.gdsc.earthgardener.domain.signup.SignUpRepository

val repositoryModule = module {
    single<CheckEmailRepository> { CheckEmailRepositoryImpl(get()) }
    single<CheckNicknameRepository> { CheckNicknameRepositoryImpl(get()) }
    single<TreeInfoRepository> { TreeInfoRepositoryImpl(get()) }
    single<TreeNameRepository> { TreeNameRepositoryImpl(get()) }
    single<PostRepository>{ PostRepositoryImpl(get()) }
    single<SignInRepository>{ SignInRepositoryImpl(get()) }
    single<ProfileRepository>{ ProfileRepositoryImpl(get())}
    single<SignUpRepository>{ SignUpRepositoryImpl(get()) }
    single<ModifyProfileRepository>{ ModifyProfileRepositoryImpl(get()) }
}