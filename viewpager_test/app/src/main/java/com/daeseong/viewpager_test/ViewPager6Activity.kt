package com.daeseong.viewpager_test


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager


class ViewPager6Activity : AppCompatActivity() {

    private val tag = ViewPager6Activity::class.java.simpleName

    private var viewPager6: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager6)

        viewPager6 = findViewById<ViewPager>(R.id.view_pager6)
        viewPager6!!.adapter = Pagar6Adapter(supportFragmentManager);
    }


    class Pagar6Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var images = intArrayOf(
            R.drawable.number1,
            R.drawable.number2,
            R.drawable.number3,
            R.drawable.number4
        )

        override fun getItem(position: Int): Fragment {
            return ItemFragment.newInstance(images[position])
        }

        override fun getCount(): Int {
            return images.size
        }
    }
}
