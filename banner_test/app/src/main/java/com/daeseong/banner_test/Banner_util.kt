package com.daeseong.banner_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class BannerUtil private constructor() {

    companion object {
        private var instance: BannerUtil? = null
        fun getInstance(): BannerUtil {
            return instance ?: BannerUtil().also { instance = it }
        }
    }

    private var bannerItems: ArrayList<RowItem>? = null
    private var completeBannerItem1: Boolean = false

    fun setDownloadBanner() {

        val urls = arrayOf(
            "https://.png",
            "https://.png",
            "https://.png",
            "https://.png"
        )
        Banner1Task().execute(*urls)
    }

    fun getDownloadBanner(): List<RowItem> {
        return bannerItems ?: emptyList()
    }

    fun getCompleteBannerItem1(): Boolean {
        return completeBannerItem1
    }

    private inner class Banner1Task : AsyncTask<String, Int, List<RowItem>>() {

        override fun onPostExecute(result: List<RowItem>) {
            completeBannerItem1 = true
        }

        override fun doInBackground(vararg params: String): List<RowItem> {
            completeBannerItem1 = false
            bannerItems = ArrayList()

            for (url in params) {
                val bitmap = downloadImage(url)

                if (bitmap == null) {
                    val imageName = StringUtil.getImageName(url)
                    val resourceId = if (imageName == "logo.png") {
                        R.drawable.number2
                    } else {
                        R.drawable.number1
                    }
                    bannerItems?.add(RowItem(BitmapFactory.decodeResource(MyApplication.getInstance().resources, resourceId)))
                } else {
                    bannerItems?.add(RowItem(bitmap))
                }
            }
            return bannerItems ?: emptyList()
        }

        private fun downloadImage(sUrl: String): Bitmap? {
            var httpURLConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            var bitmap: Bitmap? = null

            try {
                val url = URL(sUrl)
                httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.allowUserInteraction = false
                httpURLConnection.instanceFollowRedirects = true
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.connect()

                val resCode: Int = httpURLConnection.responseCode
                if (resCode != HttpURLConnection.HTTP_OK) {
                    return null
                }
                inputStream = httpURLConnection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                httpURLConnection?.disconnect()
                inputStream?.close()
            }
            return bitmap
        }
    }
}
