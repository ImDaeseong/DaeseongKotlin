package com.daeseong.newbanner_test.Banner5_style

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class BannerAdapter(private val context: Context, private var imgRes: IntArray) : PagerAdapter() {

    private val tag = javaClass.simpleName

    private var mBannerImgs: MutableList<ImageView> = mutableListOf()
    private var listener: OnItemClickListener? = null

    init {
        initImageView()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val iv: ImageView = mBannerImgs[position]
        iv.setImageResource(imgRes[position])
        iv.setOnClickListener { listener?.onItemClick(position) }
        container.addView(iv)
        return iv
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun getCount(): Int {
        return imgRes.size
    }

    private fun initImageView() {
        mBannerImgs.clear()
        for (i in imgRes) {
            val iv = ImageView(context)
            iv.scaleType = ImageView.ScaleType.CENTER_CROP
            iv.setImageResource(i)
            mBannerImgs.add(iv)
        }
    }

    fun setData(resIds: IntArray) {
        imgRes = resIds
        initImageView()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}