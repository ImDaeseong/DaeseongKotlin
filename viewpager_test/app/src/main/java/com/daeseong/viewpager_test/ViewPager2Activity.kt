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

    private lateinit var viewPager2: ViewPager
    private lateinit var arrayList: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        arrayList = ArrayList()
        arrayList.apply {
            add(R.drawable.number1)
            add(R.drawable.number2)
            add(R.drawable.number3)
            add(R.drawable.number4)
        }

        viewPager2 = findViewById(R.id.viewPager2)

        val adapter2 = Adapter2(layoutInflater, arrayList)
        viewPager2.adapter = adapter2
    }

    internal class Adapter2(private val inflater: LayoutInflater, private val arrayList: ArrayList<Int>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = inflater.inflate(R.layout.pager2_adapter, container, false)
            val imageView = view.findViewById<ImageView>(R.id.imageview2)

            val resId = arrayList[position]

            imageView.apply {
                scaleType = ImageView.ScaleType.FIT_XY
                setImageResource(resId)
                setOnClickListener { v ->
                    Snackbar.make(v, "position:$position", Snackbar.LENGTH_LONG).show()
                }
            }

            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun getCount(): Int {
            return arrayList.size
        }
    }

}
