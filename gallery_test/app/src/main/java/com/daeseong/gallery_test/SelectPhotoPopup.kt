package com.daeseong.gallery_test

import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SelectPhotoPopup : AppCompatActivity() {

    private val tag = SelectPhotoPopup::class.java.simpleName

    private var screenwidth = 0
    private var screenLength = 0
    private lateinit var clClose: View
    private lateinit var grView: GridView
    private lateinit var imageViewAdapter: PopupViewAdapter
    private lateinit var pProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_select_photo_popup)

        setFinishOnTouchOutside(false)  //다른 영역 클릭 방지

        init()

        changeDisplay()

        loadData()
    }

    private fun loadData() {
        Handler().postDelayed({
            runOnUiThread {
                initImages()
                pProgressBar.visibility = View.GONE
            }
        }, 1000)
    }

    private fun initImages() {

        /*
        //내부저장소 경로에서 파일 읽어오기
        for (item in SearchImage.getAllPicture()!!) {
            val bitmap: Bitmap = SearchImage.loadBitmap(item)
            val title: String = SearchImage.getFileName(item)
            imageViewAdapter!!.addPhoto(ImageItem(bitmap, title))
        }
        */

        val imageResources = intArrayOf(
            R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
            R.drawable.bg5, R.drawable.bg6, R.drawable.bg7, R.drawable.bg8
        )

        for (resId in imageResources) {
            val bitmap = BitmapFactory.decodeResource(resources, resId)
            val title = resources.getResourceEntryName(resId)
            imageViewAdapter.addPhoto(ImageItem(bitmap, title))
        }
    }

    private fun changeDisplay() {
        try {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenLength = size.y
            screenwidth = size.x

            if (screenLength > 0 && screenwidth > 0) {
                val viewWidth = screenwidth - dip2px(56F)
                window.setLayout(viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
                val wmlp = window.attributes
                wmlp.gravity = Gravity.CENTER
                wmlp.x = 0
                wmlp.y = 0
                window.attributes = wmlp
            }
        } catch (e: Exception) {
        }
    }

    private fun init() {

        grView = findViewById(R.id.grView)
        imageViewAdapter = PopupViewAdapter(this)
        grView.adapter = imageViewAdapter

        grView.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as ImageItem
            Toast.makeText(this@SelectPhotoPopup, item.title, Toast.LENGTH_SHORT).show()
        }

        // 닫기
        clClose = findViewById(R.id.clClose)
        clClose.setOnClickListener {
            finish()
        }

        pProgressBar = findViewById(R.id.progressbar1)
    }

    private fun dip2px(dpValue: Float): Int {
        val scale: Float = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
