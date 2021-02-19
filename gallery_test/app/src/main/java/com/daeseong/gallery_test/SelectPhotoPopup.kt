package com.daeseong.gallery_test


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SelectPhotoPopup : AppCompatActivity() {

    private val tag = SelectPhotoPopup::class.java.simpleName

    private var screenwidth = 0
    private var screenLength = 0
    private var clClose: View? = null
    private var grView: GridView? = null
    private var imageViewAdapter: PopupViewAdapter? = null
    private var pProgressBar: ProgressBar? = null

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

        Handler().postDelayed(Runnable {
            runOnUiThread {

                initImages()

                pProgressBar!!.visibility = View.GONE
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

        val bg1 = BitmapFactory.decodeResource(resources, R.drawable.bg1)
        val bg2 = BitmapFactory.decodeResource(resources, R.drawable.bg2)
        val bg3 = BitmapFactory.decodeResource(resources, R.drawable.bg3)
        val bg4 = BitmapFactory.decodeResource(resources, R.drawable.bg4)
        val bg5 = BitmapFactory.decodeResource(resources, R.drawable.bg5)
        val bg6 = BitmapFactory.decodeResource(resources, R.drawable.bg6)
        val bg7 = BitmapFactory.decodeResource(resources, R.drawable.bg7)
        val bg8 = BitmapFactory.decodeResource(resources, R.drawable.bg8)
        imageViewAdapter!!.addPhoto(ImageItem(bg1, "bg1"))
        imageViewAdapter!!.addPhoto(ImageItem(bg2, "bg2"))
        imageViewAdapter!!.addPhoto(ImageItem(bg3, "bg3"))
        imageViewAdapter!!.addPhoto(ImageItem(bg4, "bg4"))
        imageViewAdapter!!.addPhoto(ImageItem(bg5, "bg5"))
        imageViewAdapter!!.addPhoto(ImageItem(bg6, "bg6"))
        imageViewAdapter!!.addPhoto(ImageItem(bg7, "bg7"))
        imageViewAdapter!!.addPhoto(ImageItem(bg8, "bg8"))
    }

    private fun changeDisplay() {

        try {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenLength = size.y
            screenwidth = size.x

            if (screenLength > 0 && screenwidth > 0) {
                val ViewWidth = screenwidth - dip2px(this, 56F)
                window.setLayout(ViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
                val WMLP = window.attributes
                WMLP.gravity = Gravity.CENTER
                WMLP.x = 0
                WMLP.y = 0
                window.attributes = WMLP
            }
        } catch (e: Exception) {
        }
    }

    private fun init() {

        grView = findViewById<GridView>(R.id.grView)

        imageViewAdapter = PopupViewAdapter(this)

        grView!!.adapter = imageViewAdapter
        grView!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->

            try {
                val item = parent.getItemAtPosition(position) as ImageItem
                Toast.makeText(this@SelectPhotoPopup, item.getTitle(), Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
            }
        }

        //닫기
        clClose = findViewById<View>(R.id.clClose)
        clClose!!.setOnClickListener(View.OnClickListener { finish() })

        pProgressBar = findViewById<ProgressBar>(R.id.progressbar1)
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
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
