package com.daeseong.paging3_test.fun6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class ItemViewModel(private val apiService: GetApiService) : ViewModel() {

    fun getItem(query: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { ItemPagingSource(apiService, query) }
        ).flow.cachedIn(viewModelScope)
    }
}
