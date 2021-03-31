package com.techs.data.local

import android.content.Context
import android.content.SharedPreferences


class AppPreferences private constructor(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun putToken(token: String) {
        runEdit {
            it.putString(KEY_TOKEN, token)
        }
    }

    fun getToken() = preferences.getString(KEY_TOKEN, null)

    fun clearData() {
        preferences.edit().clear().apply()
    }

    private fun runEdit(putFun: (prefs: SharedPreferences.Editor) -> Unit) {
        preferences.edit().run {
            putFun.invoke(this)
            apply()
        }
    }

    companion object {
        @Volatile
        private var instance: AppPreferences? = null

        @JvmStatic
        fun get(context: Context): AppPreferences {
            if (instance == null) {
                synchronized(AppPreferences) {
                    if (instance == null) {
                        instance = AppPreferences(context)
                        return instance!!
                    }
                }
            }
            return instance!!
        }

        private const val PREFS_NAME = "ValetAppLocalData"
        private const val KEY_TOKEN = "KEY_TOKEN"
    }
}