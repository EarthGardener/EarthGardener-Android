package team.gdsc.earthgardener.presentation.user.signup.retrofit

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignUpRetrofitClient {
    val sRetrofit = initRetrofit()
    private const val BASE_URL = "http://52.78.175.39:8080/"

    private fun initRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}