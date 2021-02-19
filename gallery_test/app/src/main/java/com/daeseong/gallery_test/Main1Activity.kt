package com.daeseong.gallery_test


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private var grView: GridView? = null
    private var imageViewAdapter: ImageViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        grView = findViewById<GridView>(R.id.grView)
        imageViewAdapter = ImageViewAdapter(this, R.layout.grid_item, initImages(), true)
        grView!!.adapter = imageViewAdapter

        grView!!.onItemClickListener =  OnItemClickListener { adapterView, view, i, l ->

            val item = adapterView.getItemAtPosition(i) as ImageItem
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initImages(): ArrayList<ImageItem> {

        val imageItems: ArrayList<ImageItem> = ArrayList()

        try {

            /*
            //내부저장소 경로에서 파일 읽어오기
            for (item in SearchImage.getAllPicture()!!) {
                val bitmap: Bitmap = SearchImage.loadBitmap(item)
                val title: String = SearchImage.getFileName(item)
                imageItems.add(ImageItem(bitmap, title))
            }
            */


            val bg1 = BitmapFactory.decodeResource(resources, R.drawable.bg1)
            val bg2 = BitmapFactory.decodeResource(resources, R.drawable.bg2)
            val bg3 = BitmapFactory.decodeResource(resources, R.drawable.bg3)
            val bg4 = BitmapFactory.decodeResource(resources, R.drawable.bg4)
            val bg5 = BitmapFactory.decodeResource(resources, R.drawable.bg5)
            val bg6 = BitmapFactory.decodeResource(resources, R.drawable.bg6)
            val bg7 = BitmapFactory.decodeResource(resources, R.drawable.bg7)
            val bg8 = BitmapFactory.decodeResource(resources, R.drawable.bg8)
            imageItems.add(ImageItem(bg1, "bg1"))
            imageItems.add(ImageItem(bg2, "bg2"))
            imageItems.add(ImageItem(bg3, "bg3"))
            imageItems.add(ImageItem(bg4, "bg4"))
            imageItems.add(ImageItem(bg5, "bg5"))
            imageItems.add(ImageItem(bg6, "bg6"))
            imageItems.add(ImageItem(bg7, "bg7"))
            imageItems.add(ImageItem(bg8, "bg8"))

            return imageItems

        } catch (e: Exception) {
        }

        return imageItems
    }
}
