package com.daeseong.viewpager_test

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import java.util.*

class ViewPager5Activity : AppCompatActivity() {

    private val tag = ViewPager5Activity::class.java.simpleName

    private lateinit var toolbar: Toolbar
    private lateinit var viewPager5: ViewPager

    private lateinit var handler: Handler
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_view_pager5)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "제목"

        val pagar5Adapter = Pagar5Adapter(this)

        viewPager5 = findViewById(R.id.viewPager5)
        viewPager5.adapter = pagar5Adapter

        handler = Handler()

        val runnable = Runnable {
            viewPager5.currentItem = (viewPager5.currentItem + 1) % pagar5Adapter.count
        }

        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 2000, 2000)
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        try {
            //안드로이드 8.0 오레오 버전에서만 오류 발생
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        } catch (ex: Exception) {
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    class Pagar5Adapter(private val context: Context) : PagerAdapter() {

        private lateinit var inflater: LayoutInflater
        private val images = intArrayOf(
            R.drawable.number1,
            R.drawable.number2,
            R.drawable.number3,
            R.drawable.number4
        )

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater.inflate(R.layout.pager5_adapter, container, false)

            val imageview5 = view.findViewById<ImageView>(R.id.imageview5)
            imageview5.setImageResource(images[position])
            imageview5.setOnClickListener { v ->
                Snackbar.make(v, "Image ${position + 1}", Snackbar.LENGTH_LONG).show()
            }

            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return images.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
    }

}
