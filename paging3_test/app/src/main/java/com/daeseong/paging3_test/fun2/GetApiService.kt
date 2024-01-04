package com.daeseong.paging3_test.fun2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface GetApiService {

    @GET
    suspend fun search(@Url url: String): Response<ItemResponse>
}
