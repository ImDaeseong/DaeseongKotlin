package com.daeseong.encryptedsharedpreference_test.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.io.IOException
import java.security.GeneralSecurityException

class EncryptedSharedPreferencesUtil private constructor(context: Context) {

    companion object {

        private const val FILE_NAME = "ShareData"

        private var instance: EncryptedSharedPreferencesUtil? = null

        fun getInstance(context: Context): EncryptedSharedPreferencesUtil {
            return instance ?: EncryptedSharedPreferencesUtil(context).also { instance = it }
        }
    }

    private val strMasterkey: String by lazy {
        try {
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        } catch (e: GeneralSecurityException) {
            throw RuntimeException("Failed to create MasterKey", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to create MasterKey", e)
        }
    }

    private val encryptedSharedPreferences: SharedPreferences by lazy {
        try {
            EncryptedSharedPreferences.create(
                FILE_NAME,
                strMasterkey,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: GeneralSecurityException) {
            throw RuntimeException("Failed to create EncryptedSharedPreferences", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to create EncryptedSharedPreferences", e)
        }
    }

    fun setValue(sKey: String?, oData: Any) {
        with(encryptedSharedPreferences.edit()) {
            when (oData) {
                is String -> putString(sKey, oData)
                is Int -> putInt(sKey, oData)
                is Boolean -> putBoolean(sKey, oData)
                is Float -> putFloat(sKey, oData)
                is Long -> putLong(sKey, oData)
            }
            apply()
        }
    }

    operator fun getValue(sKey: String?, oData: Any): Any? {
        return when (oData) {
            is String -> encryptedSharedPreferences.getString(sKey, oData)
            is Int -> encryptedSharedPreferences.getInt(sKey, oData)
            is Boolean -> encryptedSharedPreferences.getBoolean(sKey, oData)
            is Float -> encryptedSharedPreferences.getFloat(sKey, oData)
            is Long -> encryptedSharedPreferences.getLong(sKey, oData)
            else -> null
        }
    }

    fun remove(sKey: String?) {
        encryptedSharedPreferences.edit().remove(sKey).apply()
    }

    fun clear() {
        encryptedSharedPreferences.edit().clear().apply()
    }

    operator fun contains(sKey: String?): Boolean {
        return encryptedSharedPreferences.contains(sKey)
    }

    fun getAll(): Map<String?, *>? {
        return encryptedSharedPreferences.all
    }
}
