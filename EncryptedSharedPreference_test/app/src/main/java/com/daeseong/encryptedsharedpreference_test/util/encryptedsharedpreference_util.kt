package com.daeseong.encryptedsharedpreference_test.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.io.IOException
import java.security.GeneralSecurityException

class encryptedsharedpreference_util(context: Context) {

    private val TAG = encryptedsharedpreference_util::class.java.simpleName

    var context: Context = context

    companion object {

        private val FILE_NAME = "ShareData"

        private var EncsharedPreferences: SharedPreferences? = null

        private var instance: encryptedsharedpreference_util? = null
        fun getInstance(context: Context): encryptedsharedpreference_util {
            if (instance == null) {
                instance = encryptedsharedpreference_util(context)
            }
            return instance as encryptedsharedpreference_util
        }
    }

    init {

        var strMasterkey: String? = null

        try {
            strMasterkey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            EncsharedPreferences = EncryptedSharedPreferences.create(
                FILE_NAME,
                strMasterkey!!,
                context!!,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun setValue(sKey: String?, oData: Any) {
        when (oData.javaClass.simpleName) {
            "String" -> {
                EncsharedPreferences!!.edit().putString(sKey, oData as String).apply()
            }
            "Integer" -> {
                EncsharedPreferences!!.edit().putInt(sKey, (oData as Int)).apply()
            }
            "Boolean" -> {
                EncsharedPreferences!!.edit().putBoolean(sKey, (oData as Boolean)).apply()
            }
            "Float" -> {
                EncsharedPreferences!!.edit().putFloat(sKey, (oData as Float)).apply()
            }
            "Long" -> {
                EncsharedPreferences!!.edit().putLong(sKey, (oData as Long)).apply()
            }
        }
    }

    operator fun getValue(sKey: String?, oData: Any): Any? {
        when (oData.javaClass.simpleName) {
            "String" -> {
                return EncsharedPreferences!!.getString(sKey, oData as String)
            }
            "Integer" -> {
                return EncsharedPreferences!!.getInt(sKey, (oData as Int))
            }
            "Boolean" -> {
                return EncsharedPreferences!!.getBoolean(sKey, (oData as Boolean))
            }
            "Float" -> {
                return EncsharedPreferences!!.getFloat(sKey, (oData as Float))
            }
            "Long" -> {
                return EncsharedPreferences!!.getLong(sKey, (oData as Long))
            }
            else -> return null
        }
    }

    fun remove(sKey: String?) {
        EncsharedPreferences!!.edit().remove(sKey).apply()
    }

    fun clear() {
        EncsharedPreferences!!.edit().clear().apply()
    }

    operator fun contains(sKey: String?): Boolean {
        return EncsharedPreferences!!.contains(sKey)
    }

    fun getAll(): Map<String?, *>? {
        return EncsharedPreferences!!.all
    }
}