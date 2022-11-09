package com.daeseong.changedeprecated

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.changedeprecated.Common.rxUtil.GetDataBitmap
import com.daeseong.changedeprecated.Common.rxUtil.GetDataString
import io.reactivex.rxjava3.functions.Consumer

class Main3Activity : AppCompatActivity() {

    private  val  tag = Main3Activity::class.java.simpleName

    private var imageView1: android.widget.ImageView? = null
    private var textView1: TextView? = null
    private var button1: Button? = null
    private var button2: Button? = null

    private val sPngUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
    private val sStrUrl = "https://api.bithumb.com/public/ticker/BTC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        imageView1 = findViewById(R.id.imageView1)
        textView1 = findViewById(R.id.textView1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            GetDataBitmap(sPngUrl)
                ?.subscribe(Consumer { bBitmap: Bitmap? ->

                    try {
                        if (bBitmap != null) {
                            imageView1!!.setImageBitmap(bBitmap)
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }

                })
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            GetDataString(sStrUrl)
                ?.subscribe(Consumer { sResult: String? ->

                    try {
                        if (sResult != null) {
                            textView1!!.text = sResult
                        }
                    } catch (ex: java.lang.Exception) {
                        ex.printStackTrace()
                    }

                })
        })
    }
}