package com.daeseong.retrifit_test

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TickerApiClient {
    private const val BASE_URL = "https://api.bithumb.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun getTickerBTC(callback: Callback<TickerBTC>) {
        val call = apiService.getTickerBTC()
        call.enqueue(callback)
    }
}
