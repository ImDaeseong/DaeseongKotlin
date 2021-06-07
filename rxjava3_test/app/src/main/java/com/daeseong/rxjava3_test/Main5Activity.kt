package com.daeseong.rxjava3_test

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.rxjava3_test.Common.DownloadJson
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class Main5Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    private var textView1: TextView? = null

    private val sUrl = "https://api.bithumb.com/public/ticker/BTC"
    private val sImgUrl = "https://.png"

    private var downloadJson: DownloadJson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        textView1 = findViewById<View>(R.id.textView1) as TextView

        downloadJson = DownloadJson()
        downloadJson!!.getData(sUrl)
            .subscribeOn(Schedulers.io())
            .onErrorComplete()
            .subscribe(Consumer { sResult: String? ->
                try {
                    textView1!!.text = sResult
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            })

        downloadJson!!.getBitmap(sUrl)
            ?.subscribeOn(Schedulers.io())
            ?.onErrorComplete()
            ?.subscribe(Consumer { bBitmap: Bitmap? ->
                try {
                    Log.e(tag, bBitmap.toString())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}