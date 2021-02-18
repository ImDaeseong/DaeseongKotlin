package com.daeseong.viewpager_test


import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
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

    private var toolbar: Toolbar? = null
    private var viewPager5: ViewPager? = null

    var handler: Handler? = null
    var runnable: Runnable? = null
    var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_view_pager5)

        toolbar  = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "제목"


        var pagar5Adapter = Pagar5Adapter(this)

        viewPager5 = findViewById<ViewPager>(R.id.viewPager5)
        viewPager5!!.adapter = pagar5Adapter


        handler = Handler()

        runnable = Runnable {

            var i = viewPager5!!.currentItem

            i++

            viewPager5!!.setCurrentItem(i, true)
        }

        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler!!.post(runnable)
            }
        }, 2000, 2000)

    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    class Pagar5Adapter(private val context: Context) : PagerAdapter() {

        private var inflater: LayoutInflater? = null
        private var images = intArrayOf(
            R.drawable.number1,
            R.drawable.number2,
            R.drawable.number3,
            R.drawable.number4
        )

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater!!.inflate(R.layout.pager5_adapter, container, false)


            val imageview5 = view.findViewById<View>(R.id.imageview5) as ImageView
            imageview5.setImageResource(images[position])
            imageview5.setOnClickListener { v ->

                Snackbar.make(v,"Image" + (position + 1), Snackbar.LENGTH_LONG).show()
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
            container.removeView(obj as View?)
        }
    }

}
