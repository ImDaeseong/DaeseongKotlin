package com.daeseong.paging3_test.fun2

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ItemViewModel : ViewModel() {

    fun getItem(url: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { ItemPagingSource(url) }
        ).flow
    }
}
