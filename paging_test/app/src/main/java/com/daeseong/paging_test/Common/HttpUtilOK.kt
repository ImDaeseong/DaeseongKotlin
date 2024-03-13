package com.daeseong.paging_test.Common

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtilOK {

    private val client = OkHttpClient()

    fun getData(address: String?, callback: Callback?) {
        if (address.isNullOrEmpty() || callback == null) return

        val request = Request.Builder()
            .url(address)
            .build()

        client.newCall(request).enqueue(callback)
    }

    fun cancelAll() {
        client.dispatcher.cancelAll()
    }
}