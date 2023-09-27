package com.daeseong.changedeprecated.Common

import ThreadTask
import com.daeseong.changedeprecated.Common.HttpUtil.getDataString

class GetStringTask : ThreadTask<String?, String?>() {

    override fun doInBackground(sUrl: String?): String? {
        return try {
            getDataString(sUrl)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
