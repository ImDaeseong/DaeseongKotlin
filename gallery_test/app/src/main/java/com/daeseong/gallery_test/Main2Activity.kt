package com.daeseong.gallery_test

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private lateinit var grView: GridView
    private lateinit var imageViewAdapter: ImageViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        grView = findViewById(R.id.grView)
        imageViewAdapter = ImageViewAdapter(this, R.layout.grid_item1, initImages(), false)
        grView.adapter = imageViewAdapter

        grView.setOnItemClickListener { _, _, i, _ ->
            val item = imageViewAdapter.getItem(i) as ImageItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initImages(): ArrayList<ImageItem> {

        val imageItems: ArrayList<ImageItem> = ArrayList()

        try {
            val imageResources = intArrayOf(
                R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
                R.drawable.bg5, R.drawable.bg6, R.drawable.bg7, R.drawable.bg8
            )

            for (resId in imageResources) {
                val bitmap = BitmapFactory.decodeResource(resources, resId)
                val title = resources.getResourceEntryName(resId)
                imageItems.add(ImageItem(bitmap, title))
            }

        } catch (e: Exception) {
        }

        return imageItems
    }

}
