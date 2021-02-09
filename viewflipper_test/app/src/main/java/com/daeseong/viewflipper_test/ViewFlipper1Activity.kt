package com.daeseong.viewflipper_test

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity


class ViewFlipper1Activity : AppCompatActivity() {

    private val tag: String = ViewFlipper1Activity::class.java.simpleName

    private var button1: Button? = null
    private var button2:Button? = null
    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null
    private var viewFlipper1: ViewFlipper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper1)

        title = tag

        imageView1 = findViewById<ImageView>(R.id.imageView1)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView3 = findViewById<ImageView>(R.id.imageView3)

        viewFlipper1 = findViewById<ViewFlipper>(R.id.viewFlipper1)
        viewFlipper1!!.inAnimation = AnimationUtils.loadAnimation(
            this,
            android.R.anim.slide_in_left
        )
        viewFlipper1!!.outAnimation = AnimationUtils.loadAnimation(
            this,
            android.R.anim.slide_out_right
        )

        //var childID = viewFlipper1!!.displayedChild
        //var childCount = viewFlipper1!!.childCount
        //Log.e(tag, "childID:$childID childCount:$childCount")

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            viewFlipper1!!.startFlipping()
            viewFlipper1!!.flipInterval = 2000
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            viewFlipper1!!.stopFlipping()
        }

        viewFlipper1!!.setOnClickListener {

            val childID = viewFlipper1!!.displayedChild
            val childCount = viewFlipper1!!.childCount

            //Log.e(tag, "childID:$childID childCount:$childCount")

            if (childCount == childID + 1) {
                viewFlipper1!!.displayedChild = 0
            } else {
                viewFlipper1!!.displayedChild = childID + 1
            }
        }

    }
}
