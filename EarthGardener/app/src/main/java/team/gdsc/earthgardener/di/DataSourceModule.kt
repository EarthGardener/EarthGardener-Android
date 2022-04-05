package team.gdsc.earthgardener.di

import org.koin.dsl.module
import team.gdsc.earthgardener.data.datasource.signup.email.CheckEmailDataSource
import team.gdsc.earthgardener.data.datasource.signup.email.CheckEmailRemoteDataSource
import team.gdsc.earthgardener.data.datasource.signup.nickname.CheckNicknameDataSource
import team.gdsc.earthgardener.data.datasource.signup.nickname.CheckNicknameRemoteDataSource
import team.gdsc.earthgardener.data.datasource.tree.TreeInfoDataSource
import team.gdsc.earthgardener.data.datasource.tree.TreeInfoRemoteDataSource
import team.gdsc.earthgardener.data.datasource.tree.TreeNameDataSource
import team.gdsc.earthgardener.data.datasource.tree.TreeNameRemoteDataSource
import team.gdsc.earthgardener.data.datasource.post.PostRemoteDataSource
import team.gdsc.earthgardener.data.datasource.post.PostDataSource
import team.gdsc.earthgardener.data.datasource.profile.ProfileDataSource
import team.gdsc.earthgardener.data.datasource.profile.ProfileRemoteDataSource
import team.gdsc.earthgardener.data.datasource.profile.modifypassword.ModifyPasswordDataSource
import team.gdsc.earthgardener.data.datasource.profile.modifypassword.ModifyPasswordRemoteDataSource
import team.gdsc.earthgardener.data.datasource.profile.modifyprofile.ModifyProfileDataSource
import team.gdsc.earthgardener.data.datasource.profile.modifyprofile.ModifyProfileRemoteDataSource
import team.gdsc.earthgardener.data.datasource.signin.SignInDataSource
import team.gdsc.earthgardener.data.datasource.signin.SignInRemoteDataSource
import team.gdsc.earthgardener.data.datasource.signin.kakao.KakaoSignInDataSource
import team.gdsc.earthgardener.data.datasource.signin.kakao.KakaoSignInRemoteDataSource
import team.gdsc.earthgardener.data.datasource.signup.SignUpDataSource
import team.gdsc.earthgardener.data.datasource.signup.SignUpRemoteDataSource
import team.gdsc.earthgardener.domain.profile.modifypassword.ModifyPasswordData

val dataSourceModule = module {
    single<CheckEmailDataSource> { CheckEmailRemoteDataSource(get()) }
    single<CheckNicknameDataSource> { CheckNicknameRemoteDataSource(get()) }
    single<TreeInfoDataSource> { TreeInfoRemoteDataSource(get()) }
    single<TreeNameDataSource> { TreeNameRemoteDataSource(get()) }
    single<PostDataSource>{ PostRemoteDataSource(get()) }
    single<SignInDataSource>{ SignInRemoteDataSource(get()) }
    single<ProfileDataSource>{ ProfileRemoteDataSource(get())}
    single<SignUpDataSource>{ SignUpRemoteDataSource(get()) }
    single<ModifyProfileDataSource>{ ModifyProfileRemoteDataSource(get()) }
    single<ModifyPasswordDataSource>{ ModifyPasswordRemoteDataSource(get())}
    single<KakaoSignInDataSource>{ KakaoSignInRemoteDataSource(get())}
}