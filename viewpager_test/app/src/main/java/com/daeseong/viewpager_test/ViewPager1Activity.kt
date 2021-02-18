package com.daeseong.viewpager_test


import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar


class ViewPager1Activity : AppCompatActivity() {

    private val tag: String = ViewPager1Activity::class.java.simpleName

    private var viewPager1: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager1)

        viewPager1 = findViewById<ViewPager>(R.id.viewPager1)

        val Adapter1 = Adapter1(this)
        viewPager1!!.adapter = Adapter1
    }


    internal class Adapter1(var context: Context) : PagerAdapter() {

        private var layoutInflater: LayoutInflater? = null

        private var Images = intArrayOf(
            R.drawable.number1,
            R.drawable.number2,
            R.drawable.number3,
            R.drawable.number4
        )

        private var lstColors = intArrayOf(
            Color.rgb(55, 55, 55),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89),
            Color.rgb(1, 188, 212)
        )

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            layoutInflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = layoutInflater!!.inflate(R.layout.pager1_adapter, container, false)

            val imageView1 = view.findViewById<View>(R.id.imageview1) as ImageView
            imageView1.setImageResource(Images[position])
            imageView1.setOnClickListener { v ->

                Snackbar.make( v,"position:$position", Snackbar.LENGTH_LONG).show()
            }

            val linearLayout = view.findViewById<LinearLayout>(R.id.imgLinearLayout)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                linearLayout.setBackgroundColor(lstColors[position])
            }

            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj : Any) {
            container.removeView(obj as View?)
        }

        override fun isViewFromObject(view: View, obj : Any): Boolean {
            return view === obj
        }

        override fun getCount(): Int {
            return Images.size
        }
    }
}
