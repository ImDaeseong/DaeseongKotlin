package com.daeseong.volley_test

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.TextView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import java.util.*

object volleypostdata {

    fun getPostData( sUrl: String?, context: Context?, progressDialog: ProgressDialog, tv2: TextView, vararg params: String) {
        val sr: StringRequest = object : StringRequest(Method.POST, sUrl, volleypostListener(context, progressDialog, tv2), volleypostErrorListener(context, progressDialog)) {
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
        sr.tag = "getPostData"
        MyApplication.getRequestQueue().add(sr)
    }
}

internal class volleypostListener(private val context: Context?, private val progressDialog: ProgressDialog, private val tv2: TextView) : Response.Listener<String?> {

    companion object {
        private val TAG = volleypostListener::class.java.simpleName
    }

    override fun onResponse(response: String?) {

        //Log.e(TAG, "volleypostListener onResponse: $response")

        tv2.text = response
        val nTop = tv2.layout.getLineTop(tv2.lineCount)
        val nScrollY = nTop - tv2.height
        if (nScrollY > 0) {
            tv2.scrollTo(0, nScrollY)
        } else {
            tv2.scrollTo(0, 0)
        }
        progressDialog.dismiss()
    }
}

internal class volleypostErrorListener(private val context: Context?, private val progressDialog: ProgressDialog) : Response.ErrorListener {

    companion object {
        private val TAG = volleypostErrorListener::class.java.simpleName
    }

    override fun onErrorResponse(error: VolleyError) {
        Log.e(TAG, "volleypostErrorListener onErrorResponse: " + error.message.toString())
        progressDialog.dismiss()
    }
}