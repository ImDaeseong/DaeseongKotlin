package com.daeseong.api_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object OkHttpUtil {

    private val tag: String = OkHttpUtil::class.java.simpleName

    private val client = OkHttpClient()

    fun getFileNameUrl(url: String): String {
        return url.substringAfterLast('/')
    }

    fun setImageViewFromFile(fFile: File, iv : ImageView) {
        val bitmap = BitmapFactory.decodeFile(fFile.absolutePath)
        iv.setImageBitmap(bitmap)
    }

    fun getCurrentTime(): String {
        var dateTime = ""
        try {
            val sformat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            dateTime = sformat.format(Date()) + "Z"
        } catch (e: Exception) {
        }
        return dateTime
    }

    interface DownloadImageCallback {
        fun onSuccess(bitmap: Bitmap)
        fun onFailure(e: Exception)
    }

    // 비동기적으로 이미지 다운로드
    fun getDownloadImage(sUrl: String, callback: DownloadImageCallback) {
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

    // 동기적으로 이미지 다운로드
    fun getDownload(sUrl: String): Bitmap? {
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

    // GET 요청으로 JSON 데이터 가져오기
    fun getDataResult(sUrl: String, callback: Callback) {
        val request = Request.Builder()
            .url(sUrl)
            .build()
        client.newCall(request).enqueue(callback)
    }

    // POST 요청으로 JSON 데이터 전송
    fun sendDataResult(sUrl: String, sParams: String, callback: Callback) {

        val requestBody = sParams.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url(sUrl)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(callback)
    }

    // POST 요청으로 JSON 데이터 전송 (헤더 추가)
    fun sendDataResultheader(sUrl: String, sParams: String, callback: Callback) {

        val requestBody = sParams.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(sUrl)
            .header("Content-Type", "application/json; charset=UTF-8")
            .header("Accept", "application/json")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(callback)
    }

    // POST 요청으로 JSON 데이터 전송 (Base64.encodeToString)
    fun sendDataResultEncode(sUrl: String, sParams: String, callback: Callback) {

        val sPassword = "myPassword"
        val sID = "myID"
        val sDateTime = getCurrentTime()
        val sByte = sID.toByteArray(Charsets.UTF_8)
        val sByteEnc = Base64.encodeToString(sByte, Base64.DEFAULT).trim()
        val sAuthorization = "$sByteEnc:$sPassword"

        val requestBody = sParams.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(sUrl)
            .header("Authorization", sAuthorization)
            .header("DateTime", sDateTime)
            .header("Content-Type", "application/json; charset=UTF-8")
            .header("Accept", "application/json")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(callback)
    }
}