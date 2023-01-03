package com.daeseong.newbanner_test.Banner7_style

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.viewpager.widget.PagerAdapter

abstract class RecyclingPagerAdapter internal constructor(private val recycleBin: RecycleBin) : PagerAdapter() {

    companion object {
        const val IGNORE_ITEM_VIEW_TYPE = AdapterView.ITEM_VIEW_TYPE_IGNORE
    }

    constructor() : this(RecycleBin()) {

    }

    init {
        recycleBin.setViewTypeCount(viewTypeCount)
    }

    override fun notifyDataSetChanged() {

        recycleBin.scrapActiveViews()
        super.notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val viewType = getItemViewType(position)
        var view: View? = null
        if (viewType != IGNORE_ITEM_VIEW_TYPE) {
            view = recycleBin.getScrapView(position, viewType)
        }

        view = getView(position, view, container)
        container.addView(view)
        return view!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val view = `object` as View
        container.removeView(view)
        val viewType = getItemViewType(position)
        if (viewType != IGNORE_ITEM_VIEW_TYPE) {
            recycleBin.addScrapView(view, position, viewType)
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    val viewTypeCount: Int
        get() = 1

    fun getItemViewType(position: Int): Int {
        return 0
    }

    abstract fun getView(position: Int, convertView: View?, container: ViewGroup?): View?

}