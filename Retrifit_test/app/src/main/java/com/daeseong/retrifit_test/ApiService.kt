package com.daeseong.retrifit_test
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("public/ticker/BTC")
    fun getTickerBTC(): Call<TickerBTC>
}
