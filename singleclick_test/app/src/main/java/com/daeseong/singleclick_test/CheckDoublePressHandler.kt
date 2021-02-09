package com.daeseong.singleclick_test

import android.util.Log

class CheckDoublePressHandler {

    private val tag: String = CheckDoublePressHandler::class.java.simpleName

    private var lastTime: Long = 0

    companion object {

        private var instance: CheckDoublePressHandler? = null

        fun getInstance(): CheckDoublePressHandler {

            if (instance == null) {

                synchronized(CheckDoublePressHandler::class.java) {
                    instance = CheckDoublePressHandler()
                }
            }
            return instance as CheckDoublePressHandler
        }
    }

    fun isDoubleClick() : Boolean {

        //Log.e(tag , "isDoubleClick start: $lastTime")

        if (System.currentTimeMillis() - lastTime <= 500) {

            //Log.e(tag , "onCheckDoublePressed")
            return true
        }
        lastTime = System.currentTimeMillis()

        //Log.e(tag , "isDoubleClick end: $lastTime")

        return false
    }
}