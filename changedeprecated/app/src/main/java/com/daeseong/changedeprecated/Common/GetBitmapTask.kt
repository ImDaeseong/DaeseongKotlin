package com.daeseong.changedeprecated.Common

import android.graphics.Bitmap
import com.daeseong.changedeprecated.Common.HttpUtil.GetDataBitmap

class GetBitmapTask : ThreadTask<String?, Bitmap?>() {

    private var bm: Bitmap? = null

    override fun doInBackground(sUrl: String?): Bitmap {

        try {
            bm = GetDataBitmap(sUrl!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bm!!
    }
}