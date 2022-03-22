package com.daeseong.volley_test

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley

class MainActivity2 : AppCompatActivity() {

    private val TAG = MainActivity2::class.java.simpleName

    private var button1: Button? = null
    private var iv1: ImageView? = null

    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initVolley()

        iv1 = findViewById<ImageView>(R.id.iv1)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            requestQueue!!.add(requestImage())
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            if (requestQueue != null) {
                requestQueue!!.cancelAll(this)
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private fun initVolley() {
        requestQueue = Volley.newRequestQueue(this)
    }

    private fun requestImage(): ImageRequest? {
        val sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
        val nMaxWidth = 0
        val nMaxHeight = 0
        val ir = ImageRequest(
            sUrl,
            { response -> iv1!!.setImageBitmap(response) },
            nMaxWidth,
            nMaxHeight,
            ImageView.ScaleType.CENTER,
            Bitmap.Config.ALPHA_8
        ) { error ->
            Log.e(TAG, "requestImage onErrorResponse: " + error.message.toString())
        }
        ir.retryPolicy = DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        ir.setShouldCache(false)
        return ir
    }
}