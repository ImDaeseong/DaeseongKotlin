package com.daeseong.paging3_test.fun4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class ItemViewModel : ViewModel() {

    private val apiService: GetApiService = RetrofitClient.getApiServiceInstance().create(GetApiService::class.java)

    fun getItem(url: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { ItemPagingSource(apiService, url) }
        ).flow.cachedIn(viewModelScope)
    }
}