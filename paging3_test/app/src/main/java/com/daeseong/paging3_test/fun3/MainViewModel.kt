package com.daeseong.paging3_test.fun3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    val imageFlow: Flow<PagingData<String>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { ImagePagingSource() }
    ).flow.cachedIn(viewModelScope)
}
