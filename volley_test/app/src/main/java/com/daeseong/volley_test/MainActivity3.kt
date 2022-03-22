package com.daeseong.volley_test

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity3 : AppCompatActivity() {

    private val TAG = MainActivity3::class.java.simpleName

    private var button1: Button? = null
    private var tv1: TextView? = null
    private var tv2:TextView? = null

    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        initVolley()

        tv1 = findViewById<TextView>(R.id.tv1)
        tv1!!.movementMethod = ScrollingMovementMethod()

        tv2 = findViewById<TextView>(R.id.tv2)
        tv2!!.movementMethod = ScrollingMovementMethod()

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            //get
            requestQueue!!.add(requestGetJson())
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

    private fun requestGetJson(): JsonObjectRequest? {

        val sUrl = "https://api.bithumb.com/public/ticker/BTC"
        val jr = JsonObjectRequest(Request.Method.GET, sUrl, null,
            { response ->
                try {
                    tv1!!.text = response.toString()
                    val sStatus = response.getString("status")
                    if (sStatus == "0000") {
                        val data = response.getJSONObject("data")
                        val opening_price = data.getString("opening_price")
                        val closing_price = data.getString("closing_price")
                        val min_price = data.getString("min_price")
                        val max_price = data.getString("max_price")
                        val units_traded = data.getString("units_traded")
                        val acc_trade_value = data.getString("acc_trade_value")
                        val prev_closing_price = data.getString("prev_closing_price")
                        val units_traded_24H = data.getString("units_traded_24H")
                        val acc_trade_value_24H = data.getString("acc_trade_value_24H")
                        val fluctate_24H = data.getString("fluctate_24H")
                        val fluctate_rate_24H = data.getString("fluctate_rate_24H")
                        val date = data.getString("date")
                        val stringBuilder = StringBuilder()
                        stringBuilder.append(opening_price)
                        stringBuilder.append("\n")
                        stringBuilder.append(closing_price)
                        stringBuilder.append("\n")
                        stringBuilder.append(min_price)
                        stringBuilder.append("\n")
                        stringBuilder.append(max_price)
                        stringBuilder.append("\n")
                        stringBuilder.append(units_traded)
                        stringBuilder.append("\n")
                        stringBuilder.append(acc_trade_value)
                        stringBuilder.append("\n")
                        stringBuilder.append(prev_closing_price)
                        stringBuilder.append("\n")
                        stringBuilder.append(units_traded_24H)
                        stringBuilder.append("\n")
                        stringBuilder.append(acc_trade_value_24H)
                        stringBuilder.append("\n")
                        stringBuilder.append(fluctate_24H)
                        stringBuilder.append("\n")
                        stringBuilder.append(fluctate_rate_24H)
                        stringBuilder.append("\n")
                        stringBuilder.append(date)
                        tv2!!.text = stringBuilder.toString()
                    }
                } catch (ex: java.lang.Exception) {
                    Log.e(TAG, "onResponse Exception: " + ex.message.toString())
                }
            }
        ) { error ->
            Log.e(TAG,"requestGetData onErrorResponse: " + error.message.toString())
        }
        jr.retryPolicy = DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        jr.setShouldCache(false)
        return jr
    }
}