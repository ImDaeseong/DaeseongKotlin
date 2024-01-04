package com.daeseong.paging3_test.fun3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class ImagePagingSource : PagingSource<Int, String>() {

    private companion object {
        private val tag = ImagePagingSource::class.java.name
    }

    private val imageList = listOf(
        "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
        "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
        "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
        "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
        "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png",
    )

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val nextPageKey = currentLoadingPageKey + 1

            if (currentLoadingPageKey <= 0) {
                return LoadResult.Error(IllegalArgumentException("Invalid page key"))
            }

            //Log.e(tag, "params.loadSize: ${params.loadSize}")
            //Log.e(tag, "currentLoadingPageKey: $currentLoadingPageKey")

            val start = (currentLoadingPageKey - 1) * params.loadSize
            val end = start + params.loadSize
            val items = if (start < imageList.size) {
                imageList.subList(start, minOf(end, imageList.size))
            } else {
                emptyList()
            }

            //Log.e(tag, "load:$items")

            LoadResult.Page(
                data = items,
                prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1,
                nextKey = if (items.isEmpty()) null else nextPageKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition
    }
}
