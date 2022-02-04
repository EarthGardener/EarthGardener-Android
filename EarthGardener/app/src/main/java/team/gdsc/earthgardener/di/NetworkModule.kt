package team.gdsc.earthgardener.di

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.gdsc.earthgardener.data.api.TreeService
import team.gdsc.earthgardener.data.sharedpref.EarthGardenerSharedPreference

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(
                            "X-AUTH-TOKEN",
                            EarthGardenerSharedPreference.getUserToken()
                        )
                        .build()
                )
            })
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setLenient().create())
            )
            .baseUrl("http://52.78.175.39:8080/")
            .build()
    }

    single<TreeService> {
        get<Retrofit>().create(TreeService::class.java)
    }
}