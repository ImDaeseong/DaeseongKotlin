package com.daeseong.api_test

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*

class Api2Activity : AppCompatActivity() {

    private val tag: String = Api2Activity::class.java.simpleName

    private lateinit var ivlist: List<ImageView>
    private lateinit var iv1: ImageView
    private lateinit var iv2: ImageView

    private val urls = listOf(
        "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
        "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api2)

        iv1 = findViewById(R.id.iv1)
        iv2 = findViewById(R.id.iv2)
        ivlist = listOf(iv1, iv2)

        //Glide 이용
        //func1()

        //okhttp3 이용
        //func2()

        //OkHttpUtil
        func3()
    }



    //OkHttpUtil
    private fun func3() {

        //val fFile = File(OkHttpUtil.getFileNameUrl(sUrl))
        //Log.e(tag, "fFile:$fFile")

        for (i in urls.indices) {
            val sUrl = urls[i]
            OkHttpUtil.getDownloadImage(sUrl, object : OkHttpUtil.DownloadImageCallback {
                override fun onSuccess(bitmap: Bitmap) {

                    runOnUiThread {
                        if (i == 0) {
                            iv1.setImageBitmap(bitmap)
                        } else if(i == 1) {
                            iv2.setImageBitmap(bitmap)
                        }
                    }
                }

                override fun onFailure(e: Exception) {
                    Log.e(tag, "Failed to download image: ${e.message}")
                }
            })
        }

    }
    //OkHttpUtil



    //okhttp3 이용
    private fun func2() {

        CoroutineScope(Dispatchers.Main).launch {
            val deferredImages = urls.map { url ->

                async(Dispatchers.IO) {
                    OkHttpUtil.getDownload(url)
                }
            }

            val images = deferredImages.awaitAll()

            images.forEachIndexed { index, bitmap ->
                when (index) {
                    0 -> iv1.setImageBitmap(bitmap)
                    1 -> iv2.setImageBitmap(bitmap)
                    else -> {
                    }
                }
            }
        }
    }
    //okhttp3 이용



    //Glide 이용
    private fun func1() {

        CoroutineScope(Dispatchers.Main).launch {
            try {

                val deferredImages = urls.map { url ->
                    async(Dispatchers.IO) { getDownload(url) }
                }

                val images = deferredImages.awaitAll()
                updateUI(images)

            } catch (e: Exception) {
            }
        }
    }

    private suspend fun getDownload(url: String): ByteArray {
        return withContext(Dispatchers.IO) {

            try {
                val glide = Glide.with(this@Api2Activity)
                val futureTarget = glide.asFile().load(url).submit()
                val file = futureTarget.get()
                file.readBytes()
            } catch (e: Exception) {
                ByteArray(0)
            }
        }
    }

    private fun updateUI(images: List<ByteArray>) {

        images.forEachIndexed { index, image ->

            Glide.with(this@Api2Activity)
                .load(image)
                .placeholder(R.drawable.a)
                .into(ivlist[index])
        }
    }
    //Glide 이용



}