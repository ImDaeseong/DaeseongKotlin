package com.daeseong.singleton_test.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


class SharedPreferencesutil {

    private val tag: String = SharedPreferencesutil::class.java.simpleName;

    private var sharedPreferences: SharedPreferences? = null

    private val FILE_NAME = "ShareData"

    companion object {
        private var instance: SharedPreferencesutil? = null
        fun getInstance(): SharedPreferencesutil {
            if (instance == null) {
                instance = SharedPreferencesutil()
            }
            return instance as SharedPreferencesutil
        }
    }

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun setData(sKey: String, oData: Any) {

        when(oData) {
            is String -> sharedPreferences!!.edit().putString(sKey, oData).commit()
            is Int -> sharedPreferences!!.edit().putInt(sKey, oData).commit()
            is Boolean -> sharedPreferences!!.edit().putBoolean(sKey, oData).commit()
            is Float -> sharedPreferences!!.edit().putFloat(sKey, oData).commit()
            is Long -> sharedPreferences!!.edit().putLong(sKey, oData).commit()
            else -> Log.e(tag, "")
        }
    }

    fun getData(sKey: String, oData: Any): Any {

        return when(oData) {
            is String -> sharedPreferences!!.getString(sKey, oData.toString())!!
            is Int -> sharedPreferences!!.getInt(sKey, oData)
            is Boolean -> sharedPreferences!!.getBoolean(sKey, oData)
            is Float -> sharedPreferences!!.getFloat(sKey, oData)
            is Long -> sharedPreferences!!.getLong(sKey, oData)
            else -> sharedPreferences!!.getString(sKey, oData.toString())!!
        }
    }

    fun remove(sKey: String) {
        val editor = sharedPreferences!!.edit()
        editor.remove(sKey)
        editor.commit()
    }

    fun clear() {
        val editor = sharedPreferences!!.edit()
        editor.clear()
        editor.commit()
    }

    operator fun contains(sKey: String): Boolean {
        return sharedPreferences!!.contains(sKey)
    }
}