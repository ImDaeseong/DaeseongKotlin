package com.daeseong.api_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException

object OkHttpUtil {

    private val tag: String = OkHttpUtil::class.java.simpleName

    /*
    fun getFileNameUrl(url: String): String {
        return url.substringAfterLast('/')
    }

    fun setImageViewFromFile(fFile: File, iv : ImageView) {
        val bitmap = BitmapFactory.decodeFile(fFile.absolutePath)
        iv.setImageBitmap(bitmap)
    }
    */


    interface DownloadImageCallback {
        fun onSuccess(bitmap: Bitmap)
        fun onFailure(e: Exception)
    }

    //bitmap download
    fun getDownloadImage(sUrl: String, callback: DownloadImageCallback) {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(sUrl)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                if (!response.isSuccessful) {
                    callback.onFailure(IOException("Unexpected response code: ${response.code}"))
                    return
                }

                response.body?.byteStream()?.use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    bitmap?.let {
                        callback.onSuccess(it)
                    } ?: run {
                        callback.onFailure(IOException("Failed to decode bitmap"))
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e)
            }
        })
    }

    //bitmap download
    fun getDownload(sUrl: String): Bitmap? {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(sUrl)
            .build()

        return try {
            client.newCall(request).execute().body?.use { response ->
                BitmapFactory.decodeStream(response.byteStream())
            }
        } catch (e: IOException) {
            null
        }
    }


    //get method - json download
    fun getDataResult(sUrl: String, callback: Callback) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(sUrl)
            .build()
        client.newCall(request).enqueue(callback)
    }


    //post method - json
    fun sendDataResult(sUrl: String, sParams: String, callback: Callback) {

        val client = OkHttpClient()
        val requestBody = sParams.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url(sUrl)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(callback)
    }


}