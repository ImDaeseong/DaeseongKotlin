package com.daeseong.newbanner_test.Banner6_style

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener


class BannerAdapter(context: Context, imgRes: IntArray) : PagerAdapter(),  OnPageChangeListener {

    private val tag = javaClass.simpleName
    private val mBannerResIds: IntArray = imgRes
    private val mContext: Context = context
    private var listener: OnItemClickListener? = null


    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun getCount(): Int {
        return Short.MAX_VALUE.toInt()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val iv = ImageView(mContext)
        iv.scaleType = ImageView.ScaleType.CENTER_INSIDE
        iv.setImageResource(mBannerResIds[position % mBannerResIds.size])
        iv.setOnClickListener { if (listener != null) listener!!.onItemClick(position) }
        container.addView(iv)
        return iv
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}