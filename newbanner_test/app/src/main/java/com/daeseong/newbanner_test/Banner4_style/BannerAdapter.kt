package com.daeseong.newbanner_test.Banner4_style

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class BannerAdapter(private val mContext: Context, private var mBannerResIds: IntArray) : PagerAdapter() {

    private var mBannerImgs: MutableList<ImageView>? = null
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        initImageView(mContext)

        if (mBannerImgs != null && mBannerImgs!!.isNotEmpty()) {
            val iv = mBannerImgs!![position]
            iv.setImageResource(mBannerResIds[position])
            iv.setOnClickListener { listener?.onItemClick(position) }
            container.addView(iv)
            return iv
        }
        return View(mContext)
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun getCount(): Int {
        return mBannerResIds.size
    }

    private fun initImageView(context: Context) {
        mBannerImgs = ArrayList()
        for (i in mBannerResIds) {
            val iv = ImageView(context)
            iv.scaleType = ImageView.ScaleType.CENTER_CROP
            iv.setImageResource(i)
            mBannerImgs!!.add(iv)
        }
    }

    fun setData(resIds: IntArray) {
        mBannerResIds = resIds
        initImageView(mContext)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}