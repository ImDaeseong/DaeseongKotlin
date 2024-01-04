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
import com.daeseong.paging3_test.fun4.ItemAdapter
import com.daeseong.paging3_test.fun4.ItemViewModel
import kotlinx.coroutines.flow.collectLatest

class Main4Activity : AppCompatActivity() {

    private companion object {
        private val tag = Main4Activity::class.java.name
    }

    private val sUrl = "https://api.github.com/search/repositories?sort=stars&q=android"

    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val recyclerView: RecyclerView = findViewById(R.id.rv1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter()
        recyclerView.adapter = adapter

        val swl1: SwipeRefreshLayout = findViewById(R.id.swl1)
        swl1.setOnRefreshListener {
            adapter.refresh()
        }

        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getItem(sUrl).collectLatest {
                adapter.submitData(it)
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