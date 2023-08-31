package com.daeseong.retrifit_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            TickerApiClient.getTickerBTC(object : Callback<TickerBTC> {
                override fun onResponse(call: Call<TickerBTC>, response: Response<TickerBTC>) {
                    if (response.isSuccessful) {
                        val ticker = response.body()
                        if (ticker != null) {
                            val openingPrice = ticker.getData()?.getOpeningPrice()
                            val closingPrice = ticker.getData()?.getClosingPrice()
                            val minPrice = ticker.getData()?.getMinPrice()
                            val maxPrice = ticker.getData()?.getMaxPrice()
                            val unitsTraded = ticker.getData()?.getUnitsTraded()
                            val accTradeValue = ticker.getData()?.getAccTradeValue()
                            val prevClosingPrice = ticker.getData()?.getPrevClosingPrice()
                            val unitsTraded24H = ticker.getData()?.getUnitsTraded24H()
                            val accTradeValue24H = ticker.getData()?.getAccTradeValue24H()
                            val fluctate24H = ticker.getData()?.getFluctate24H()
                            val fluctateRate24H = ticker.getData()?.getFluctateRate24H()
                            val date = ticker.getData()

                            if (openingPrice != null) {
                                Log.e(tag, openingPrice)
                            }
                            if (closingPrice != null) {
                                Log.e(tag, closingPrice)
                            }
                            if (minPrice != null) {
                                Log.e(tag, minPrice)
                            }
                            if (maxPrice != null) {
                                Log.e(tag, maxPrice)
                            }
                            if (unitsTraded != null) {
                                Log.e(tag, unitsTraded)
                            }
                            if (accTradeValue != null) {
                                Log.e(tag, accTradeValue)
                            }
                            if (prevClosingPrice != null) {
                                Log.e(tag, prevClosingPrice)
                            }
                            if (unitsTraded24H != null) {
                                Log.e(tag, unitsTraded24H)
                            }
                            if (accTradeValue24H != null) {
                                Log.e(tag, accTradeValue24H)
                            }
                            if (fluctate24H != null) {
                                Log.e(tag, fluctate24H)
                            }
                            if (fluctateRate24H != null) {
                                Log.e(tag, fluctateRate24H)
                            }
                            Log.e(tag, date.toString())
                        }
                    } else {
                        Log.e(tag, response.code().toString())
                    }
                }

                override fun onFailure(call: Call<TickerBTC>, t: Throwable) {
                    Log.e(tag, t.message.toString())
                }
            })
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {

        }
    }
}