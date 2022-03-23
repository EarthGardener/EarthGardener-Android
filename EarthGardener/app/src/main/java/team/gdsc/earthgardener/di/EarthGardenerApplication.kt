package team.gdsc.earthgardener.di

import android.app.Application
import android.content.SharedPreferences
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class EarthGardenerApplication: Application() {

    companion object{
        lateinit var sSharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        val X_AUTH_TOKEN = "X-AUTH-TOKEN"
    }

    override fun onCreate() {
        super.onCreate()

        setUpTimber()

        sSharedPreferences = applicationContext.getSharedPreferences("EARTH_GARDENER", MODE_PRIVATE)
        editor = sSharedPreferences.edit()

        startKoin{
            androidContext(this@EarthGardenerApplication)
            modules(netWorkModule, dataSourceModule, repositoryModule, viewModelModule, useCaseModule)
        }

        KakaoSdk.init(this, "4d39f8358a445b85f6a3204e74bcd599")
    }

    private fun setUpTimber(){
        Timber.plant(Timber.DebugTree())
    }
}