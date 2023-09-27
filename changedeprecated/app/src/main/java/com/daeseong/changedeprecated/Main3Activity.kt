package com.daeseong.changedeprecated

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.changedeprecated.Common.RxUtil
import io.reactivex.rxjava3.disposables.Disposable

class Main3Activity : AppCompatActivity() {

    private  val  tag = Main3Activity::class.java.simpleName

    private lateinit var imageView1: ImageView
    private lateinit var textView1: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button

    private var disposable1: Disposable? = null
    private var disposable2: Disposable? = null

    private val pngUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
    private val strUrl = "https://api.bithumb.com/public/ticker/BTC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        imageView1 = findViewById(R.id.imageView1)
        textView1 = findViewById(R.id.textView1)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        button1.setOnClickListener {

            disposable1?.dispose()
            disposable1 = RxUtil.getDataBitmap(pngUrl)
                .subscribe { bitmap: Bitmap? ->
                    try {
                        bitmap?.let {
                            imageView1.setImageBitmap(it)
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

        }

        button2.setOnClickListener {

            disposable2?.dispose()
            disposable2 = RxUtil.getDataString(strUrl)
                .subscribe { result: String? ->
                    try {
                        result?.let {
                            textView1.text = it
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable1?.dispose()
        disposable2?.dispose()
    }
}