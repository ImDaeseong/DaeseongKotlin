package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.rxjava3_test.Common.DownloadUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Main5Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    private lateinit var textView1: TextView
    private lateinit var imageView1: ImageView

    private val sUrl = "https://api.bithumb.com/public/ticker/BTC"
    private val sImgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
    private lateinit var downloadUtil: DownloadUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        textView1 = findViewById(R.id.textView1)
        imageView1 = findViewById(R.id.imageView1)

        downloadUtil = DownloadUtil()

        downloadUtil.getData(sUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { sResult: String? ->
                    textView1.text = sResult
                },
                { throwable: Throwable? ->
                    throwable?.printStackTrace()
                }
            )

        downloadUtil.getBitmap(sImgUrl)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { bBitmap: Bitmap? ->
                    //Log.e(tag, bBitmap.toString())
                    imageView1.setImageBitmap(bBitmap)
                },
                { throwable: Throwable? ->
                    throwable?.printStackTrace()
                }
            )
    }
}
