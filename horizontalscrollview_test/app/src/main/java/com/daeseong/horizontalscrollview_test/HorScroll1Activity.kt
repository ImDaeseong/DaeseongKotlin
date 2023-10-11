package com.daeseong.horizontalscrollview_test

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HorScroll1Activity : AppCompatActivity() {

    private val tag = HorScroll1Activity::class.java.simpleName

    private lateinit var imageViewList: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hor_scroll1)

        imageViewList = listOf(
            findViewById(R.id.imageView3),
            findViewById(R.id.imageView4),
            findViewById(R.id.imageView5),
            findViewById(R.id.imageView6),
            findViewById(R.id.imageView7),
            findViewById(R.id.imageView8),
            findViewById(R.id.imageView9),
            findViewById(R.id.imageView10)
        )

        setClickListeners()
    }

    private fun setClickListeners() {
        imageViewList.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                Log.e(tag, "imageView${index + 3}")
            }
        }
    }
}
