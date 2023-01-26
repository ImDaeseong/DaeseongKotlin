package com.daeseong.banner_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import com.daeseong.banner_test.String_util.getImageName
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Banner_util {

    private val tag = Banner_util::class.java.name

    companion object {
        private var instance: Banner_util? = null
        fun getInstance(): Banner_util {
            if (instance == null) {
                instance = Banner_util()
            }
            return instance as Banner_util
        }
    }

    private var BannerItem1: ArrayList<RowItem>? = null
    private var CompleteBannerItem1 : Boolean = false

    fun setDownloadBanner() {

        val url1 = "https://.png"
        val url2 = "https://.png"
        val url3 = "https://.png"
        val url4 = "https://.png"
        Banner1Task().execute(*arrayOf(url1, url2, url3, url4) )
    }

    fun getDownloadBanner(): List<RowItem> {
        return BannerItem1!!
    }

    fun getCompleteBannerItem1(): Boolean {
        return CompleteBannerItem1
    }

    private inner class Banner1Task :  AsyncTask<String, Int, List<RowItem>>() {

        override fun onPostExecute(result: List<RowItem>) {

            //Log.e(tag, "onPostExecute")

            CompleteBannerItem1 = true
        }

        override fun doInBackground(vararg params: String): List<RowItem> {

            //Log.e(tag, "doInBackground")

            CompleteBannerItem1 = false

            var bitmap: Bitmap? = null
            BannerItem1 = ArrayList()

            for (url in params) {

                //Log.e(tag, "url:$url")

                /*
                Log.e(tag, "getExt:" + String_util.getExt(url))
                Log.e(tag, "getImageName:" + getImageName(url))
                Log.e(tag, "getUpper:" + String_util.getUpper(String_util.getExt(url)))
                Log.e(tag, "getLower:" + String_util.getLower(String_util.getExt(url)))
                Log.e(tag, "getMoneyDecimalFormat:" + String_util.getMoneyDecimalFormat("100000"))
                Log.e(tag, "getrandomUUID:" + String_util.getrandomUUID())
                Log.e(tag, "sTime:" + String_util.getTime())
                Log.e(
                    tag, "sTime1:" + String_util.getDate(
                        String_util.getTime(),
                        "yyyy-MM-dd hh:mm:ss"
                    )
                )
                Log.e(tag, "isExpired:" + String_util.isExpired("2018-02-03"))
                Log.e(tag, "removeQuoted:" + String_util.removeQuoted("\"test\""))
                Log.e(tag, "isPhoneNo:" + String_util.isPhoneNo("010-1234-5678"))
                Log.e(tag, "isEmail:" + String_util.isEmail("test@test.co"))
                Log.e(tag, "isNumeric:" + String_util.isNumeric("101030305"))
                Log.e(tag, "isNumericInc:" + String_util.isNumericInc("ABD-3030-A"))
                Log.e(tag, "getBannerIndex:" + String_util.getBannerIndex())
                */

                bitmap = DownLoadImage(url)

                //오류로 인해 이미지를 받아오지 못할 경우 가지고 있는 리소스를 이용한다.
                if (bitmap == null) {

                    val sImg = getImageName(url)
                    if(sImg == "logo.png"){
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().resources, R.drawable.number2)
                    }else{
                        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().resources, R.drawable.number1)
                    }
                }
                BannerItem1!!.add(RowItem(bitmap!!))

            }
            return BannerItem1!!
        }

        private fun DownLoadImage(sUrl: String): Bitmap? {

            var httpURLConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            val bufferedReader: BufferedReader? = null
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
                inputStream = httpURLConnection.getInputStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
                httpURLConnection.disconnect()

            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return bitmap
        }

    }

}