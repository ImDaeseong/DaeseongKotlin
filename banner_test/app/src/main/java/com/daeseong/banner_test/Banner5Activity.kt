package com.daeseong.banner_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.banner_test.String_util.getImageName
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Banner5Activity : AppCompatActivity() {

    private val tag = Banner5Activity::class.java.simpleName

    private val map = HashMap<String, Bitmap>()

    private val map1 = HashMap<String, Bitmap>()

    private var imageView1: ImageView? = null
    private var imageView2:ImageView? = null
    private var imageView3:ImageView? = null
    private var imageView4: ImageView? = null
    private var button1: Button? = null

    private var bClick = false

    val url1 = "https://.png"
    val url2 = "https://.png"
    val url3 = "https://.png"
    val url4 = "https://.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner5)

        imageView1 = findViewById<ImageView>(R.id.imageView1)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView3 = findViewById<ImageView>(R.id.imageView3)
        imageView4 = findViewById<ImageView>(R.id.imageView4)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            if (bClick) {
                bClick = false
                Banner4Task().execute(*arrayOf(url1, url2, url3, url4))
            } else {
                bClick = true
                val thread = Banner4Thread(arrayOf(url1, url2, url3, url4))
                thread.start()
            }
        }

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
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
        return bitmap
    }

    inner class Banner4Thread(private val params: Array<String>) : Thread() {

        override fun run() {
            try {
                var sFileName = ""
                var bitmap: Bitmap? = null

                for (url in params) {
                    sFileName = getImageName(url)
                    bitmap = DownLoadImage(url)

                    //오류로 인해 이미지를 받아오지 못할 경우 가지고 있는 리소스를 이용한다.
                    if (bitmap == null) {
                        when (sFileName) {
                            "logo1.png" -> bitmap = BitmapFactory.decodeResource(
                                MyApplication.getInstance().resources,
                                R.drawable.number1
                            )
                            "logo2.png" -> bitmap = BitmapFactory.decodeResource(
                                MyApplication.getInstance().resources,
                                R.drawable.number2
                            )
                            "logo3.png" -> bitmap = BitmapFactory.decodeResource(
                                MyApplication.getInstance().resources,
                                R.drawable.number3
                            )
                            "logo4.png" -> bitmap = BitmapFactory.decodeResource(
                                MyApplication.getInstance().resources,
                                R.drawable.number4
                            )
                        }
                    }

                    if(bitmap != null){
                        map1[sFileName] = bitmap!!
                    }
                }

                //Log.e(tag, "Thread bitmap Download complete")

                for (key in map1.keys) {
                    when (key) {
                        "logo1.png" -> {
                            val bBkey: Bitmap? = map1[key]
                            imageView1!!.post { imageView1!!.setImageBitmap(bBkey) }
                        }
                        "logo2.png" -> {
                            val bBkey: Bitmap? = map1[key]
                            imageView2!!.post { imageView2!!.setImageBitmap(bBkey) }
                        }
                        "logo3.png" -> {
                            val bBkey: Bitmap? = map1[key]
                            imageView3!!.post { imageView3!!.setImageBitmap(bBkey) }
                        }
                        "logo4.png" -> {
                            val bBkey: Bitmap? = map1[key]
                            imageView4!!.post { imageView4!!.setImageBitmap(bBkey) }
                        }
                    }
                }

                map1.clear()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    inner  class Banner4Task :  AsyncTask<String?, Void?, Boolean>() {

        override fun doInBackground(vararg params: String?): Boolean? {

            var sFileName = ""
            var bitmap: Bitmap? = null

            for (url in params) {

                sFileName = getImageName(url!!)
                bitmap = DownLoadImage(url!!)

                //오류로 인해 이미지를 받아오지 못할 경우 가지고 있는 리소스를 이용한다.
                if (bitmap == null) {
                    when (sFileName) {
                        "logo1.png" -> bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().resources, R.drawable.number1)
                        "logo2.png" -> bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().resources, R.drawable.number2)
                        "logo3.png" -> bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().resources, R.drawable.number3)
                        "logo4.png" -> bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().resources, R.drawable.number4)
                    }
                }

                if(bitmap != null){
                    map[sFileName] = bitmap!!
                }
            }
            return true
        }

        override fun onPostExecute(aBoolean: Boolean) {
            super.onPostExecute(aBoolean)

            if (aBoolean) {

                for (key in map.keys) {
                    when (key) {
                        "logo1.png" -> {
                            imageView1!!.setImageBitmap(map.get(key))
                        }
                        "logo2.png" -> {
                            imageView2!!.setImageBitmap(map.get(key))
                        }
                        "logo3.png" -> {
                            imageView3!!.setImageBitmap(map.get(key))
                        }
                        "logo4.png" -> {
                            imageView4!!.setImageBitmap(map.get(key))
                        }
                    }
                }
                map.clear()
            }
        }
    }

}
