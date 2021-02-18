package com.daeseong.horizontalscrollview_test

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HorScroll1Activity : AppCompatActivity() {

    private val tag = HorScroll1Activity::class.java.simpleName

    private var imageView3: ImageView? = null
    private var imageView4:ImageView? = null
    private var imageView5:ImageView? = null
    private var imageView6:ImageView? = null
    private var imageView7:ImageView? = null
    private var imageView8:ImageView? = null
    private var imageView9:ImageView? = null
    private var imageView10:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hor_scroll1)

        imageView3 = findViewById<ImageView>(R.id.imageView3)
        imageView4 = findViewById<ImageView>(R.id.imageView4)
        imageView5 = findViewById<ImageView>(R.id.imageView5)
        imageView6 = findViewById<ImageView>(R.id.imageView6)
        imageView7 = findViewById<ImageView>(R.id.imageView7)
        imageView8 = findViewById<ImageView>(R.id.imageView8)
        imageView9 = findViewById<ImageView>(R.id.imageView9)
        imageView10 = findViewById<ImageView>(R.id.imageView10)

        imageView3!!.setOnClickListener {
            Log.e(tag, "imageView3")
        }

        imageView4!!.setOnClickListener {
            Log.e(tag, "imageView4")
        }

        imageView5!!.setOnClickListener {
            Log.e(tag, "imageView5")
        }

        imageView6!!.setOnClickListener {
            Log.e(tag, "imageView6")
        }

        imageView7!!.setOnClickListener {
            Log.e(tag, "imageView7")
        }

        imageView8!!.setOnClickListener {
            Log.e(tag, "imageView8")
        }

        imageView9!!.setOnClickListener {
            Log.e(tag, "imageView9")
        }

        imageView10!!.setOnClickListener {
            Log.e(tag, "imageView10")
        }

    }
}
