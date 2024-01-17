package com.daeseong.coil_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.Coil
import coil.request.ImageRequest

class Coil1Activity : AppCompatActivity() {

    private val sPngUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    private lateinit var iv1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coil1)

        iv1 = findViewById<ImageView>(R.id.iv1)

        loadImage()
    }

    private fun loadImage() {
        val request = ImageRequest.Builder(this)
            .data(sPngUrl)
            .target(iv1)
            .build()

        Coil.imageLoader(this).enqueue(request)
    }
}