package com.im.daeseong.singleton_test.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil private constructor() {

    companion object {
        private var instance: SharedPreferencesUtil? = null
        fun getInstance(): SharedPreferencesUtil {
            return instance ?: SharedPreferencesUtil().also { instance = it }
        }
    }

    private val FILE_NAME = "ShareData"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun setValue(sKey: String, oData: Any) {

        val sType = oData.javaClass.simpleName
        val editor = sharedPreferences.edit()

        when (sType) {
            "String" -> editor.putString(sKey, oData as String)
            "Integer" -> editor.putInt(sKey, oData as Int)
            "Boolean" -> editor.putBoolean(sKey, oData as Boolean)
            "Float" -> editor.putFloat(sKey, oData as Float)
            "Long" -> editor.putLong(sKey, oData as Long)
        }
        editor.apply()
    }

    fun getValue(sKey: String, oData: Any): Any? {

        val sType = oData.javaClass.simpleName

        return when (sType) {
            "String" -> sharedPreferences.getString(sKey, oData as String)
            "Integer" -> sharedPreferences.getInt(sKey, oData as Int)
            "Boolean" -> sharedPreferences.getBoolean(sKey, oData as Boolean)
            "Float" -> sharedPreferences.getFloat(sKey, oData as Float)
            "Long" -> sharedPreferences.getLong(sKey, oData as Long)
            else -> null
        }
    }

    fun remove(sKey: String) {
        val editor = sharedPreferences.edit()
        editor.remove(sKey)
        editor.apply()
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun contains(sKey: String): Boolean {
        return sharedPreferences.contains(sKey)
    }

    fun getAll(): Map<String, *> {
        return sharedPreferences.all
    }
}
