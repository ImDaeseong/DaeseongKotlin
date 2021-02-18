package com.daeseong.viewpager_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import java.util.*


class ViewPager2Activity : AppCompatActivity() {

    private val tag: String = ViewPager2Activity::class.java.simpleName

    private var viewPager2: ViewPager? = null
    private var arrayList: ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        arrayList = ArrayList()
        arrayList!!.add(R.drawable.number1)
        arrayList!!.add(R.drawable.number2)
        arrayList!!.add(R.drawable.number3)
        arrayList!!.add(R.drawable.number4)

        viewPager2 = findViewById<ViewPager>(R.id.viewPager2)

        var Adapter2 = Adapter2(layoutInflater, arrayList!!)
        viewPager2!!.adapter = Adapter2
    }


    internal class Adapter2( private val inflater: LayoutInflater, private val arrayList: ArrayList<Int> ) :  PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val view: View = inflater.inflate(R.layout.pager2_adapter, null)
            val imageView = view.findViewById<View>(R.id.imageview2) as ImageView

            val nRes = arrayList[position]

            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setImageResource(nRes)
            imageView.setOnClickListener { v ->

                Snackbar.make(v, "position:$position", Snackbar.LENGTH_LONG).show()
            }

            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View?)
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun getCount(): Int {
            return arrayList.size
        }
    }

}
