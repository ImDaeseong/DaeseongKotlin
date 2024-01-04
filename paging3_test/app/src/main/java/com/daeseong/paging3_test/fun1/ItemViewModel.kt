package com.daeseong.paging3_test.fun1

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ItemViewModel(private val apiService: GetApiService) : ViewModel() {

    fun getItem(query: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { ItemPagingSource(apiService, query) }
        ).flow
    }
}
