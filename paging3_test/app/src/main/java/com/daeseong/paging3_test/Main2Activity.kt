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
import com.daeseong.paging3_test.fun2.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Main2Activity : AppCompatActivity() {

    private companion object {
        private val tag = Main2Activity::class.java.name
    }

    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemAdapter

    private val sUrl = "https://api.github.com/search/repositories?sort=stars&q=android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val recyclerView: RecyclerView = findViewById(R.id.rv1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter()
        recyclerView.adapter = adapter

        val swl1 : SwipeRefreshLayout = findViewById(R.id.swl1)
        swl1.setOnRefreshListener {
            adapter.refresh()
        }

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(ItemViewModel::class.java)

        lifecycleScope.launch {
            viewModel.getItem(sUrl).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
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