package com.daeseong.changedeprecated.Common

import ThreadTask
import android.graphics.Bitmap
import com.daeseong.changedeprecated.Common.HttpUtil.getDataBitmap

class GetBitmapTask : ThreadTask<String?, Bitmap?>() {

    override fun doInBackground(param: String?): Bitmap? {
        var bm: Bitmap? = null
        try {
            bm = getDataBitmap(param!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bm
    }
}