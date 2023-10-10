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

    private lateinit var button1: Button
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView

    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        initVolley()

        tv1 = findViewById(R.id.tv1)
        tv1.movementMethod = ScrollingMovementMethod()

        tv2 = findViewById(R.id.tv2)
        tv2.movementMethod = ScrollingMovementMethod()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            // get
            requestQueue?.add(requestGetJson())
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            requestQueue?.cancelAll(this)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private fun initVolley() {
        requestQueue = Volley.newRequestQueue(this)
    }

    private fun requestGetJson(): JsonObjectRequest {
        val url = "https://api.bithumb.com/public/ticker/BTC"

        return JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    tv1.text = response.toString()
                    val status = response.getString("status")

                    if (status == "0000") {
                        val data = response.getJSONObject("data")
                        val openingPrice = data.getString("opening_price")
                        val closingPrice = data.getString("closing_price")
                        val minPrice = data.getString("min_price")
                        val maxPrice = data.getString("max_price")
                        val unitsTraded = data.getString("units_traded")
                        val accTradeValue = data.getString("acc_trade_value")
                        val prevClosingPrice = data.getString("prev_closing_price")
                        val unitsTraded24H = data.getString("units_traded_24H")
                        val accTradeValue24H = data.getString("acc_trade_value_24H")
                        val fluctuate24H = data.getString("fluctate_24H")
                        val fluctuateRate24H = data.getString("fluctate_rate_24H")
                        val date = data.getString("date")

                        val stringBuilder = StringBuilder()
                        stringBuilder.append(openingPrice).append("\n")
                        stringBuilder.append(closingPrice).append("\n")
                        stringBuilder.append(minPrice).append("\n")
                        stringBuilder.append(maxPrice).append("\n")
                        stringBuilder.append(unitsTraded).append("\n")
                        stringBuilder.append(accTradeValue).append("\n")
                        stringBuilder.append(prevClosingPrice).append("\n")
                        stringBuilder.append(unitsTraded24H).append("\n")
                        stringBuilder.append(accTradeValue24H).append("\n")
                        stringBuilder.append(fluctuate24H).append("\n")
                        stringBuilder.append(fluctuateRate24H).append("\n")
                        stringBuilder.append(date)

                        tv2.text = stringBuilder.toString()
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "onResponse Exception: ${ex.message}")
                }
            },
            { error ->
                Log.e(TAG, "requestGetJson onErrorResponse: ${error.message}")
            }
        ).apply {
            retryPolicy = DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            setShouldCache(false)
        }
    }

}