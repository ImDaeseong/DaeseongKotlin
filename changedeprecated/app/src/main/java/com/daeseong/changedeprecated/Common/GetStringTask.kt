package com.daeseong.changedeprecated.Common

import com.daeseong.changedeprecated.Common.HttpUtil.GetDataString

class GetStringTask : ThreadTask<String?, String?>() {

    private var sResult = ""

    override fun doInBackground(sUrl: String?): String {

        try {
            sResult = GetDataString(sUrl)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sResult
    }
}