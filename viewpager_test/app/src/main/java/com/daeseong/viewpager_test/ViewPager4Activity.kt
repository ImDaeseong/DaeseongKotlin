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

class ViewPager4Activity : AppCompatActivity() {

    private val tag = ViewPager4Activity::class.java.simpleName

    private lateinit var viewPager4: ViewPager
    private lateinit var pagerTitleStrip: PagerTitleStrip
    private lateinit var list: List<DataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager4)

        viewPager4 = findViewById(R.id.view_pager4)
        pagerTitleStrip = findViewById(R.id.title_strip)

        list = LoadData(this).getAll()

        val pagar4Adapter = Pagar4Adapter(this, list)
        viewPager4.adapter = pagar4Adapter
        viewPager4.addOnPageChangeListener(viewPagerPageChangeListener)
    }

    private val viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            val selectColor = list[position].bgColor
            pagerTitleStrip.setTextColor(selectColor)
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class LoadData(private val context: Context) {

        private val titles: Array<String> = context.resources.getStringArray(R.array.titles)
        private val colors: Array<String> = context.resources.getStringArray(R.array.colors)

        private val images = intArrayOf(
            R.drawable.number1,
            R.drawable.number2,
            R.drawable.number3,
            R.drawable.number4
        )

        fun getAll(): List<DataItem> {
            return images.mapIndexed { index, imgID ->
                DataItem(titles[index], imgID, Color.parseColor(colors[index]))
            }
        }
    }

    class Pagar4Adapter(private val context: Context, private val list: List<DataItem>) : PagerAdapter() {

        private var inflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater!!.inflate(R.layout.pager4_adapter, container, false)

            val bgColor = view.findViewById<LinearLayout>(R.id.bgColor)
            val imageView4: ImageView = view.findViewById(R.id.imageview4)
            val title = view.findViewById<TextView>(R.id.title)

            imageView4.setOnClickListener {
                Snackbar.make(it, "position:$position", Snackbar.LENGTH_LONG).show()
            }

            val dataItem = list[position]

            bgColor.setBackgroundColor(dataItem.bgColor)
            imageView4.setImageResource(dataItem.imgID)
            title.text = dataItem.title

            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View?)
        }
    }

}

