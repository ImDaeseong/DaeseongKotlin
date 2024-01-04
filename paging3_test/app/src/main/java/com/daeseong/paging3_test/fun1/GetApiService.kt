package com.daeseong.paging3_test.fun1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetApiService {

    @GET("search/repositories")
    suspend fun search(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<ItemResponse>
}
