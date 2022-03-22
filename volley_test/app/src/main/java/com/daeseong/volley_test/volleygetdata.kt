package com.daeseong.volley_test

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.TextView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest

object volleygetdata {

    fun getData(sUrl: String?, context: Context?, progressDialog: ProgressDialog, tv1: TextView) {
        val sr = StringRequest(Request.Method.GET, sUrl, volleygetListener(context, progressDialog, tv1), volleygetErrorListener(context, progressDialog) )
        sr.retryPolicy = DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        sr.setShouldCache(false)
        sr.tag = "getData"
        MyApplication.getRequestQueue().add(sr)
    }
}

internal class volleygetListener(private val context: Context?,  private val progressDialog: ProgressDialog, private val tv1: TextView) : Response.Listener<String?> {

    companion object {
        private val TAG = volleygetListener::class.java.simpleName
    }

    override fun onResponse(response: String?) {

        //Log.e(TAG, "volleygetListener onResponse: $response")

        tv1.text = response
        val nTop = tv1.layout.getLineTop(tv1.lineCount)
        val nScrollY = nTop - tv1.height
        if (nScrollY > 0) {
            tv1.scrollTo(0, nScrollY)
        } else {
            tv1.scrollTo(0, 0)
        }
        progressDialog.dismiss()
    }
}

internal class volleygetErrorListener( private val context: Context?, private val progressDialog: ProgressDialog) : Response.ErrorListener {

    companion object {
        private val TAG = volleygetErrorListener::class.java.simpleName
    }

    override fun onErrorResponse(error: VolleyError) {
        Log.e(TAG, "volleygetErrorListener onErrorResponse: " + error.message.toString())
        progressDialog.dismiss()
    }
}