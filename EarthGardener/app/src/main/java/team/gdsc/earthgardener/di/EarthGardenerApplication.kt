package team.gdsc.earthgardener.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import team.gdsc.earthgardener.data.sharedpref.EarthGardenerSharedPreference

class EarthGardenerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EarthGardenerApplication)
            modules(netWorkModule, dataSourceModule, repositoryModule, viewModelModule)
        }

        EarthGardenerSharedPreference.init(applicationContext)
    }
}