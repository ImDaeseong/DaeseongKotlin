package com.daeseong.paging_test.Common

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtilOK {

    private val tag = HttpUtilOK::class.java.simpleName

    fun getData(address: String?, callback: Callback?) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        client.newCall(request).enqueue(callback)
    }
}