package com.daeseong.singleton_test.util

import android.util.Log

class StringLib {

    private val tag: String = StringLib::class.java.simpleName;

    companion object {
        private var instance: StringLib? = null
        fun getInstance(): StringLib {
            if (instance == null) {
                instance = StringLib()
            }
            return instance as StringLib
        }
    }

    fun isBlank(str : String) : Boolean {

        return (str == null || str == "")

        Log.e(tag, str.isBlank().toString());
        Log.e(tag, str.isEmpty().toString());
    }

    fun getSubString(str : String, max : Int) : String {

        if ( str != null && str.length > max) {
            return str.trim().substring(0, max)
        }
        return str.trim();
    }
}