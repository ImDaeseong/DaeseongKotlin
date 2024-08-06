package com.daeseong.fcm_test

import android.content.Context

object SharedPreferencesUtil {

    public const val FILE_NAME = "fcm_testShareData"

    fun setValue(context: Context, key: String, value: Any?) {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            else -> throw IllegalArgumentException("Unsupported value type")
        }
        editor.apply()
    }

    inline fun <reified T : Any> getValue(context: Context, key: String, defaultValue: T): T {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return when (T::class) {
            String::class -> sharedPreferences.getString(key, defaultValue as? String ?: "") as T
            Int::class -> sharedPreferences.getInt(key, defaultValue as? Int ?: 0) as T
            Boolean::class -> sharedPreferences.getBoolean(key, defaultValue as? Boolean ?: false) as T
            Float::class -> sharedPreferences.getFloat(key, defaultValue as? Float ?: 0f) as T
            Long::class -> sharedPreferences.getLong(key, defaultValue as? Long ?: 0L) as T
            else -> defaultValue
        }
    }

    fun remove(context: Context, key: String) {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clear(context: Context) {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun contains(context: Context, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.contains(key)
    }

    fun getAll(context: Context): Map<String, *> {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.all
    }
}