package team.gdsc.earthgardener.data.sharedpref

import android.content.Context
import android.content.SharedPreferences

object EarthGardenerSharedPreference {
    private const val USER_ID = "USER_ID"
    private const val PET_ID = "PET_ID"
    private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserToken(): String {
        return preferences.getString(USER_ID, "") ?: ""
    }

    fun setUserToken(value: String) {
        preferences.edit().putString(USER_ID, value).apply()
    }
}