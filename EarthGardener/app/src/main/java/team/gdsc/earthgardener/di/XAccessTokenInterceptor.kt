package team.gdsc.earthgardener.di

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.X_ACCESS_TOKEN
import team.gdsc.earthgardener.di.EarthGardenerApplication.Companion.sSharedPreferences

class XAccessTokenInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sSharedPreferences.getString(X_ACCESS_TOKEN, null)
        if(jwtToken != null){
            builder.addHeader("X-AUTH-TOKEN", jwtToken)
        }
        return chain.proceed(builder.build())
    }
}