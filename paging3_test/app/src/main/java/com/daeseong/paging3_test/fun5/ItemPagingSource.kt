package com.daeseong.paging3_test.fun5

import androidx.paging.PagingSource
import androidx.paging.PagingState

class ItemPagingSource(private val aryList: ArrayList<Item>) : PagingSource<Int, Item>() {

    private companion object {
        private val tag = ItemPagingSource::class.java.name
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        try {

            val page = params.key ?: 0
            val pageSize = params.loadSize
            val start = page * pageSize
            val end = (page + 1) * pageSize
            val range = start.until(end.coerceAtMost(aryList.size))

            if (start >= aryList.size) {
                return LoadResult.Error(Exception(""))
            }

            val pageData = aryList.subList(start, range.last + 1).map {
                Item(it.id, it.name)
            }

            return LoadResult.Page(
                data = pageData,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (end >= aryList.size) null else page + 1
            )

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
}
