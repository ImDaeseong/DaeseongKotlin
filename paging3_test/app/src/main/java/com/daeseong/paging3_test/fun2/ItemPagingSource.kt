package com.daeseong.paging3_test.fun2

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class ItemPagingSource(private val url: String) : PagingSource<Int, Item>() {

    private companion object {
        private val tag = ItemPagingSource::class.java.name
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        try {
            val page = params.key ?: 1
            val response = getApiService().search("$url&page=$page&per_page=${params.loadSize}")

            //Log.e(tag, "params.loadSize: ${params.loadSize}")
            //Log.e(tag, "page: $page")

            return if (response.isSuccessful) {

                val data = response.body()?.items ?: emptyList()
                //Log.e(tag, "load:$data")

                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun getApiService(): GetApiService {
        return RetrofitClient.getApiService()
    }
}
