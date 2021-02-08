package com.daeseong.sharedpreferences_test

import android.content.Context
import android.content.SharedPreferences


class SharedPreferences_util {

    private val FILE_NAME = "ShareData"

    fun setValue(context: Context, sKey: String, oData: Any) {

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )

        when(oData) {
            is String -> sharedPreferences!!.edit().putString(sKey, oData).commit()
            is Int -> sharedPreferences!!.edit().putInt(sKey, oData).commit()
            is Boolean -> sharedPreferences!!.edit().putBoolean(sKey, oData).commit()
            is Float -> sharedPreferences!!.edit().putFloat(sKey, oData).commit()
            is Long -> sharedPreferences!!.edit().putLong(sKey, oData).commit()
        }
    }

    fun getValue(context: Context, sKey: String, oData: Any): Any {

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        return when(oData) {
            is String -> sharedPreferences!!.getString(sKey, oData.toString())!!
            is Int -> sharedPreferences!!.getInt(sKey, oData)
            is Boolean -> sharedPreferences!!.getBoolean(sKey, oData)
            is Float -> sharedPreferences!!.getFloat(sKey, oData)
            is Long -> sharedPreferences!!.getLong(sKey, oData)
            else -> sharedPreferences!!.getString(sKey, oData.toString())!!
        }
    }

    fun remove(context: Context, sKey: String?) {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(sKey)
        editor.commit()
    }

    fun clear(context: Context) {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

    fun contains(context: Context, sKey: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences!!.contains(sKey)
    }

    fun getAll(context: Context): Map<String?, *>? {
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.all
    }
}