package com.daeseong.sharedpreferences_test

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil {

    companion object {
        private const val FILE_NAME = "ShareData"
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun setValue(context: Context, key: String, data: Any) {
        val editor = getSharedPreferences(context).edit()

        when (data) {
            is String -> editor.putString(key, data)
            is Int -> editor.putInt(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Long -> editor.putLong(key, data)
        }

        editor.apply()
    }

    fun getValue(context: Context, key: String, defaultData: Any): Any {
        val sharedPreferences = getSharedPreferences(context)

        return when (defaultData) {
            is String -> sharedPreferences.getString(key, defaultData)!!
            is Int -> sharedPreferences.getInt(key, defaultData)
            is Boolean -> sharedPreferences.getBoolean(key, defaultData)
            is Float -> sharedPreferences.getFloat(key, defaultData)
            is Long -> sharedPreferences.getLong(key, defaultData)
            else -> sharedPreferences.getString(key, defaultData.toString())!!
        }
    }

    fun remove(context: Context, key: String?) {
        getSharedPreferences(context).edit().remove(key).apply()
    }

    fun clear(context: Context) {
        getSharedPreferences(context).edit().clear().apply()
    }

    fun contains(context: Context, key: String): Boolean {
        return getSharedPreferences(context).contains(key)
    }

    fun getAll(context: Context): Map<String, *> {
        return getSharedPreferences(context).all
    }
}
