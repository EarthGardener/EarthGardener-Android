package team.gdsc.earthgardener.di

import android.app.Application
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EarthGardenerApplication: Application() {

    companion object{
        lateinit var sSharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        val X_ACCESS_TOKEN = "X-AUTH-TOKEN"
    }

    override fun onCreate() {
        super.onCreate()

        sSharedPreferences = applicationContext.getSharedPreferences("EARTH_GARDENER", MODE_PRIVATE)
        editor = sSharedPreferences.edit()

        startKoin{
            androidContext(this@EarthGardenerApplication)
            modules(netWorkModule, dataSourceModule, repositoryModule, viewModelModule)
        }

    }
}