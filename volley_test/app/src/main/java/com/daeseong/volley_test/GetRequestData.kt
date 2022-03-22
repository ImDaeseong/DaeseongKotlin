package com.daeseong.volley_test

import android.content.Context
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*


class GetRequestData {

    companion object {
        private var instance: GetRequestData? = null
        fun getInstance(): GetRequestData {
            if (instance == null) {
                instance = GetRequestData()
            }
            return instance as GetRequestData
        }
    }

    private var requestQueue: RequestQueue? = null
    private var context: Context? = null

    fun init(context: Context) {
        requestQueue = Volley.newRequestQueue(context)
        this.context = context
    }

    fun clear() {
        if (requestQueue != null) {
            requestQueue!!.cancelAll(context)
        }
    }

    fun getData(sUrl: String?, listener: Response.Listener<String?>?, errorListener: Response.ErrorListener?, vararg params: String?) {
        val sr = StringRequest(Request.Method.GET, sUrl, listener, errorListener)
        sr.retryPolicy = DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        sr.setShouldCache(false)
        requestQueue!!.add(sr)
    }

    fun getPostData(sUrl: String?, listener: Response.Listener<String?>?, errorListener: Response.ErrorListener?, vararg params: String) {
        val sr: StringRequest = object : StringRequest(Method.POST, sUrl, listener, errorListener) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val param: MutableMap<String, String> = HashMap()
                param["dobType"] = params[0]
                param["dsptcKsco"] = params[1]
                param["continent"] = params[2]
                param["showItemListCount"] = params[3]
                param["sepmt61"] = params[4]
                return param
            }
        }
        sr.retryPolicy = DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        sr.setShouldCache(false)
        requestQueue!!.add(sr)
    }
}
