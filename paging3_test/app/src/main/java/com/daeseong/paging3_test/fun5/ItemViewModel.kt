package com.daeseong.paging3_test.fun5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class ItemViewModel : ViewModel() {

    private val aryList: ArrayList<Item> = arrayListOf(
        Item(0, "Item 0 "),
        Item(1, "Item 1 "),
        Item(2, "Item 2 "),
        Item(3, "Item 3 "),
        Item(4, "Item 4 "),
        Item(5, "Item 5 "),
        Item(6, "Item 6 "),
        Item(7, "Item 7 "),
        Item(8, "Item 8 "),
        Item(9, "Item 9"),
        Item(10, "Item 10"),
        Item(11, "Item 11"),
        Item(12, "Item 12"),
        Item(13, "Item 13"),
        Item(14, "Item 14"),
        Item(15, "Item 15"),
        Item(16, "Item 16"),
        Item(17, "Item 17"),
        Item(18, "Item 18"),
        Item(19, "Item 19"),
        Item(20, "Item 20")
    )

    fun getItem(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { ItemPagingSource(aryList) }
        ).flow.cachedIn(viewModelScope)
    }
}