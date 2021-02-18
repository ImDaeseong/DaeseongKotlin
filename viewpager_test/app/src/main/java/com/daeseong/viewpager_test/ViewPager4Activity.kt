package com.daeseong.viewpager_test

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.PagerTitleStrip
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList


class ViewPager4Activity : AppCompatActivity() {

    private val tag = ViewPager4Activity::class.java.simpleName

    private var viewPager4: ViewPager? = null
    private var pagerTitleStrip: PagerTitleStrip? = null
    private var list: List<DataItem> = Collections.emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager4)

        viewPager4 = findViewById<ViewPager>(R.id.view_pager4)
        pagerTitleStrip = findViewById<PagerTitleStrip>(R.id.title_strip)

        list = LoadData(this).getAll()

        var pagar4Adapter = Pagar4Adapter(this, list)
        viewPager4!!.adapter = pagar4Adapter
        viewPager4!!.addOnPageChangeListener(viewPagerPageChangeListener)
    }

    var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {

        override fun onPageSelected(position: Int) {

            val selectColor = list[position].getBgColor()

            //Log.e(tag, "onPageSelected:$selectColor")
            pagerTitleStrip!!.setTextColor(selectColor)
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class LoadData(context: Context) {

        private var titles: Array<String> = context.resources.getStringArray(R.array.titles)
        private var colors: Array<String> = context.resources.getStringArray(R.array.colors)

        private var images = intArrayOf(
            R.drawable.number1,
            R.drawable.number2,
            R.drawable.number3,
            R.drawable.number4
        )

        fun getAll(): List<DataItem> {

            val list: MutableList<DataItem> = ArrayList()
            for (i in images.indices) {
                val title = titles[i]
                val imgID = images[i]
                val color = Color.parseColor(colors[i])
                val item = DataItem(title, imgID, color)
                list.add(item)
            }
            return list
        }
    }


    class Pagar4Adapter(var context: Context, private val list : List<DataItem>) : PagerAdapter() {

        var inflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater!!.inflate(R.layout.pager4_adapter, container, false)

            val bgColor = view.findViewById(R.id.bgColor) as LinearLayout
            val imageView4: ImageView = view.findViewById(R.id.imageview4) as ImageView
            val title = view.findViewById(R.id.title) as TextView

            imageView4.setOnClickListener { v ->

                if (v != null) {
                    Snackbar.make(v, "position:$position", Snackbar.LENGTH_LONG).show()
                }
            }

            val dataItem = list[position]

            bgColor.setBackgroundColor(dataItem.getBgColor())
            imageView4.setImageResource(dataItem.getImgID())
            title.text = dataItem.getTitle()

            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj : Any) {
            container.removeView(obj as View?)
        }

    }


}

