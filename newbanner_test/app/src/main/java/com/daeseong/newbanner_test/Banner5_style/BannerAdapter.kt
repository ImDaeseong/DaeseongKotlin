package com.daeseong.newbanner_test.Banner5_style

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.viewpager.widget.PagerAdapter


class BannerAdapter(context: Context, imgRes: IntArray) : PagerAdapter() {

    private val tag = javaClass.simpleName

    private var mBannerImgs: MutableList<ImageView>? = null
    private var mBannerResIds: IntArray
    private val mContext: Context = context
    private var listener: OnItemClickListener? = null

    init {
        mBannerResIds = imgRes
        initImageView(mContext)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        if (mBannerImgs != null && mBannerImgs!!.isNotEmpty()) {
            val iv: ImageView = mBannerImgs!![position]
            iv.setImageResource(mBannerResIds[position])
            iv.setOnClickListener { if (listener != null) listener!!.onItemClick(position) }
            container.addView(iv)
            return iv
        }
        return null!!
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

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}