package com.daeseong.newbanner_test.Banner3_style

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class BannerAdapter(private val context: Context, private var imgRes: IntArray) : PagerAdapter() {

    private var bannerImgs: MutableList<ImageView> = ArrayList()
    private var listener: OnItemClickListener? = null

    fun setData(resIds: IntArray) {
        imgRes = resIds
        initImageView()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return imgRes.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val iv = bannerImgs[position]
        iv.setImageResource(imgRes[position])

        iv.setOnClickListener {
            listener?.onItemClick(position)
        }

        container.addView(iv)
        return iv
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    private fun initImageView() {
        bannerImgs = ArrayList()
        imgRes.forEach { i ->
            val iv = ImageView(context)
            iv.scaleType = ImageView.ScaleType.CENTER_CROP
            iv.setImageResource(i)
            bannerImgs.add(iv)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}