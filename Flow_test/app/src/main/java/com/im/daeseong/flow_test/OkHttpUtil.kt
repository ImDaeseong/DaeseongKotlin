package com.im.daeseong.flow_test

import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object OkHttpUtil {

    private val tag = OkHttpUtil::class.simpleName

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)// 연결 실패 시 자동 재시도 활성화(기본적으로 1번만 재시도)
            .build()
    }

    fun getDataResult(sUrl: String, callback: Callback) {
        val request = Request.Builder()
            .url(sUrl)
            .build()

        client.newCall(request).enqueue(callback)
    }

    fun postDataResult(sUrl: String, sParams: String, callback: Callback) {
        val requestBody = sParams.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url(sUrl)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(callback)
    }

    fun cancelAll() {
        client.dispatcher.cancelAll()
        Log.i(tag, "All requests canceled")
    }
}