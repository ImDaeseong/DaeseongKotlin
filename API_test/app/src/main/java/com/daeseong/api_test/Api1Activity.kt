package com.daeseong.api_test

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class Api1Activity : AppCompatActivity() {

    private val tag: String = Api1Activity::class.java.simpleName

    private lateinit var iv1: ImageView

    private val sUrl : String =  "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api1)

        iv1 = findViewById(R.id.iv1)

        //파일 이름
        val fFile = File(OkHttpUtil.getFileNameUrl(sUrl))
        Log.e(tag, "fFile:$fFile")

        //Glide 이용
        //func1()

        //okhttp3 이용
        //func2()

        //OkHttpUtil
        func3()
    }



    //OkHttpUtil
    private fun func3() {

        OkHttpUtil.getDownloadImage(sUrl, object : OkHttpUtil.DownloadImageCallback {
            override fun onSuccess(bitmap: Bitmap) {

                runOnUiThread {
                    iv1.setImageBitmap(bitmap)
                }
            }

            override fun onFailure(e: Exception) {
                Log.e(tag, "Failed to download image: ${e.message}")
            }
        })
    }
    //OkHttpUtil



    //okhttp3 이용
    private fun func2() {

        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                OkHttpUtil.getDownload(sUrl)
            }
            bitmap?.let {
                iv1.setImageBitmap(it)
            } ?: run {
            }
        }
    }
    //okhttp3 이용



    //Glide 이용
    private fun func1() {

        CoroutineScope(Dispatchers.Main).launch {
            try {

                val image = withContext(Dispatchers.IO) {
                    getDownload(sUrl)
                }

                updateUI(image)

            } catch (e: Exception) {
            }
        }
    }

    private suspend fun getDownload(url: String): ByteArray {
        return withContext(Dispatchers.IO) {

            try {
                val glide = Glide.with(this@Api1Activity)
                val futureTarget = glide.asFile().load(url).submit()
                val file = futureTarget.get()
                file.readBytes()
            } catch (e: Exception) {
                ByteArray(0)
            }
        }
    }

    private fun updateUI(image: ByteArray) {
        Glide.with(this@Api1Activity)
            .load(image)
            .placeholder(R.drawable.a)
            .into(iv1)
    }
    //Glide 이용



}