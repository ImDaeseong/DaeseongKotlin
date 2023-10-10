package com.daeseong.viewflipper_test

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity

class ViewFlipper1Activity : AppCompatActivity() {

    private val tag: String = ViewFlipper1Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var viewFlipper1: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper1)

        title = tag

        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)

        viewFlipper1 = findViewById(R.id.viewFlipper1)
        viewFlipper1.inAnimation = AnimationUtils.loadAnimation(
            this,
            android.R.anim.slide_in_left
        )
        viewFlipper1.outAnimation = AnimationUtils.loadAnimation(
            this,
            android.R.anim.slide_out_right
        )

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            viewFlipper1.startFlipping()
            viewFlipper1.flipInterval = 2000
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            viewFlipper1.stopFlipping()
        }

        viewFlipper1.setOnClickListener {
            val childID = viewFlipper1.displayedChild
            val childCount = viewFlipper1.childCount

            if (childCount == childID + 1) {
                viewFlipper1.displayedChild = 0
            } else {
                viewFlipper1.displayedChild = childID + 1
            }
        }
    }
}
