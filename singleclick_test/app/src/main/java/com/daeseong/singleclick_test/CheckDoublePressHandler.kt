package com.daeseong.singleclick_test

class CheckDoublePressHandler private constructor() {

    private val tag: String = CheckDoublePressHandler::class.java.simpleName
    private var lastTime: Long = 0

    companion object {

        private var instance: CheckDoublePressHandler? = null

        @Synchronized
        fun getInstance(): CheckDoublePressHandler {
            return instance ?: CheckDoublePressHandler().also { instance = it }
        }
    }

    fun isDoubleClick(): Boolean {
        if (System.currentTimeMillis() - lastTime <= 500) {
            return true
        }
        lastTime = System.currentTimeMillis()
        return false
    }
}
