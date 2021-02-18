package com.daeseong.viewpager_test


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener


class ViewPager3Activity : AppCompatActivity() {

    private val tag = ViewPager3Activity::class.java.simpleName

    private var viewPager: ViewPager? = null
    private var dotsLayout: LinearLayout? = null

    private lateinit var dots: Array<TextView?>
    private var layouts: IntArray? = null

    private var btnSkip : Button? = null
    private var btnNext :Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //notification bar 투명
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(R.layout.activity_view_pager3)

        viewPager = findViewById<ViewPager>(R.id.view_pager3)
        dotsLayout = findViewById<LinearLayout>(R.id.layoutDots);
        btnSkip = findViewById<Button>(R.id.btn_skip)
        btnNext = findViewById<Button>(R.id.btn_next)

        layouts = intArrayOf(
            R.layout.pager3_slide1layout,
            R.layout.pager3_slide2layout,
            R.layout.pager3_slide3layout,
            R.layout.pager3_slide4layout
        )

        addBottomDots(0)

        changeStatusBarColor()

        var pagar3Adapter = Pagar3Adapter(this)
        viewPager!!.adapter = pagar3Adapter
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)

        btnSkip!!.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btnNext!!.setOnClickListener {

            val current = getItem(+1)

            if (current < layouts!!.size) {

                // move to next screen
                viewPager!!.currentItem = current
            } else {

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {

        override fun onPageSelected(position: Int) {

            addBottomDots(position)

            if (position == layouts!!.size - 1) {

                btnNext!!.text = "End"
                btnSkip!!.visibility = View.GONE
            } else {

                btnNext!!.text = "next"
                btnSkip!!.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    private fun addBottomDots(currentPage: Int) {

        dots = arrayOfNulls(layouts!!.size)

        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        dotsLayout!!.removeAllViews()
        for (i in dots!!.indices) {
            dots!![i] = TextView(this)
            dots!![i]!!.text = Html.fromHtml("&#8226;")
            dots!![i]!!.textSize = 35F
            dots!![i]!!.setTextColor(colorsInactive[currentPage])
            dotsLayout!!.addView(dots!![i])
        }

        if (dots!!.isNotEmpty())
            dots!![currentPage]!!.setTextColor(colorsActive[currentPage])
    }

    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }

    private fun changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }


    inner class Pagar3Adapter(var context: Context) : PagerAdapter() {

        private var inflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater!!.inflate(layouts!![position], container, false)

            view.setOnClickListener {

                Log.e(tag, "position:$position")
            }

            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View?)
        }
    }
}
