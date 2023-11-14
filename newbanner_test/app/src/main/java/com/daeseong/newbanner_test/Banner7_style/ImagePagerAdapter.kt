package com.daeseong.newbanner_test.Banner7_style

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.daeseong.newbanner_test.OnSingleClickListener
import com.daeseong.newbanner_test.R

class ImagePagerAdapter(private val context: Context, private val size: Int) : RecyclingPagerAdapter() {

    private val tag = ImagePagerAdapter::class.java.simpleName

    private var listener: OnItemClickListener? = null
    private val imageIdList: List<Bitmap>? = null
    var isInfiniteLoop = false

    private val imgs = intArrayOf(R.drawable.number1, R.drawable.number2, R.drawable.number3, R.drawable.number4)

    private fun getSize(imageIdList: List<Bitmap>?): Int {
        return size
    }

    override fun getCount(): Int {
        return if (isInfiniteLoop) Int.MAX_VALUE else getSize(imageIdList)
    }

    private fun getPosition(position: Int): Int {
        return if (isInfiniteLoop) position % size else position
    }

    override fun getView(position: Int, view: View?, container: ViewGroup?): View? {

        var view = view
        val holder: ViewHolder

        if (view == null) {

            holder = ViewHolder()
            holder.imageView = ImageView(context)
            view = holder.imageView

            //화면에 맞게 full 로 채운다.
            holder.imageView!!.adjustViewBounds = true
            holder.imageView!!.scaleType = ImageView.ScaleType.FIT_XY
            view!!.setTag(-0x1, holder)

        } else {
            holder = view.getTag(-0x1) as ViewHolder
        }

        try {

            val nPos = getPosition(position)

            var sUrl = ""
            when (nPos) {
                0 -> {
                    sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
                }
                1 -> {
                    sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
                }
                2 -> {
                    sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
                }
                3 -> {
                    sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
                }
            }

            Glide.with(context)
                .load(sUrl)
                .into(holder.imageView!!)

        } catch (ex: Exception) {
            holder.imageView!!.setImageBitmap(BitmapFactory.decodeResource(view.resources, R.drawable.number1))
        }

        holder.imageView!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View?) {
                if (listener != null) listener!!.onItemClick(position)
            }
        })
        return view
    }

    private class ViewHolder {
        var imageView: ImageView? = null
    }

    fun setInfiniteLoop(isInfiniteLoop: Boolean): ImagePagerAdapter {
        this.isInfiniteLoop = isInfiniteLoop
        return this
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}