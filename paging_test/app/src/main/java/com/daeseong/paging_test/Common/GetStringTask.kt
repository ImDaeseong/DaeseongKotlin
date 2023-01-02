package com.daeseong.paging_test.Common

import com.daeseong.paging_test.Common.HttpUtil.GetDataString

class GetStringTask : ThreadTask<String?, String?>() {

    companion object {
        private val tag = GetStringTask::class.java.simpleName
    }

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
