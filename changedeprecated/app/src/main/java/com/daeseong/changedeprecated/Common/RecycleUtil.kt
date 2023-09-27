package com.daeseong.changedeprecated.Common

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import java.lang.ref.WeakReference

object RecycleUtil {

    fun recursiveRecycle(root: View?) {
        root ?: return

        root.background = null

        if (root is ViewGroup) {
            val group = root as ViewGroup
            for (i in 0 until group.childCount) {
                recursiveRecycle(group.getChildAt(i))
            }

            if (root !is AdapterView<*>) {
                group.removeAllViews()
            }
        }

        if (root is ImageView) {
            root.setImageDrawable(null)
        }
    }

    fun recursiveRecycle(recycleList: List<WeakReference<View?>>) {
        recycleList.forEach { ref -> recursiveRecycle(ref.get()) }
    }

    fun unBindDrawables(view: View) {
        view.background?.callback = null

        if (view is ViewGroup && view !is AdapterView<*>) {
            for (i in 0 until view.childCount) {
                unBindDrawables(view.getChildAt(i))
            }
            view.removeAllViews()
        }
    }
}
