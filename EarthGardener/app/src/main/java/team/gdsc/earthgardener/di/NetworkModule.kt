package team.gdsc.earthgardener.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.gdsc.earthgardener.data.api.PostService

val netWorkModule = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .baseUrl("http://52.78.175.39:8080/")
            .build()
    }

    single<PostService> {
        get<Retrofit>().create(PostService::class.java)
    }
}