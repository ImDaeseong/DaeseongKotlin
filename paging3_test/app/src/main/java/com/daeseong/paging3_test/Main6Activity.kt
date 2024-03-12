package com.daeseong.paging3_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daeseong.paging3_test.fun6.GetApiService
import com.daeseong.paging3_test.fun6.ItemAdapter
import com.daeseong.paging3_test.fun6.ItemViewModel
import com.daeseong.paging3_test.fun6.ViewModelFactory
import kotlinx.coroutines.launch

class Main6Activity : AppCompatActivity() {

    private companion object {
        private val tag = Main6Activity::class.java.name
    }

    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemAdapter
    private lateinit var rv1: RecyclerView
    private lateinit var swl1: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        initViewModel()
        initAdapter()
        initListenEvent()
        initRequest()
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(GetApiService.getData())
            )[ItemViewModel::class.java]
    }

    private fun initAdapter() {

        rv1 = findViewById(R.id.rv1)
        rv1.layoutManager = LinearLayoutManager(this)
        rv1.setHasFixedSize(true)

        adapter = ItemAdapter()
        rv1.adapter = adapter
    }

    private fun initRequest() {

        lifecycleScope.launch {
            viewModel.getItem("android").collect {
                adapter.submitData(it)
            }
        }
    }

    private fun initListenEvent() {

        swl1  = findViewById(R.id.swl1)
        swl1.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {

                is LoadState.Loading -> {
                    Log.e(tag, "Loading")
                    swl1.isRefreshing = true
                }

                is LoadState.Error -> {
                    Log.e(tag, "Error")
                    swl1.isRefreshing = false
                }

                is LoadState.NotLoading -> {
                    Log.e(tag, "NotLoading")
                    swl1.isRefreshing = false
                }
            }
        }
    }
}