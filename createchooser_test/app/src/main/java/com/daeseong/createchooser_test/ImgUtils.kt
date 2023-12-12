package com.daeseong.createchooser_test

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection

object ImgUtils {

    private val tag: String = ShareUtils::class.java.simpleName

    fun getObservableBitmap(sUrl: String): Observable<Bitmap> {
        return Observable.fromCallable(Callable<Bitmap> {
            try {
                getHttpBitmap(sUrl)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        })
            .subscribeOn(Schedulers.io())
    }

    fun getAsset(context: Context, assetFileName: String): Uri? {
        try {
            val file = File(context.cacheDir, "images/$assetFileName")
            if (!file.exists()) {
                context.assets.open(assetFileName).use { inputStream ->
                    FileOutputStream(file).use { outputStream ->
                        val buffer = ByteArray(1024)
                        var read: Int
                        while (inputStream.read(buffer).also { read = it } != -1) {
                            outputStream.write(buffer, 0, read)
                        }
                    }
                }
            }
            return FileProvider.getUriForFile(context,"com.daeseong.createchooser_test.fileprovider", file)
        } catch (ex: IOException) {
            Log.e(tag, ex.message.toString())
        }
        return null
    }

    fun getUri(context: Context, bitmap: Bitmap): Uri? {
        val resolver: ContentResolver = context.contentResolver
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "image_name")
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        val contentUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val imageUri = resolver.insert(contentUri, contentValues)
        try {
            if (imageUri != null) {
                resolver.openOutputStream(imageUri).use { outputStream ->
                    outputStream?.let {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                        return imageUri
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message, ex)
        }
        return null
    }

    private fun getHttpBitmap(sUrl: String): Bitmap? {
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {
            // SSL https 처리
            HttpsURLConnection.setDefaultHostnameVerifier(HostnameVerifier { hostname, session ->
                hostname == session.peerHost
            })

            val url = URL(sUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()
            val resCode = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            httpURLConnection?.disconnect()
        }
        return bitmap
    }
}
