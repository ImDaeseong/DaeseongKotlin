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
import com.daeseong.paging3_test.fun3.ImageAdapter
import com.daeseong.paging3_test.fun3.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Main3Activity : AppCompatActivity() {

    private companion object {
        private val tag = Main3Activity::class.java.name
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val imageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val recyclerView: RecyclerView = findViewById(R.id.rv1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = imageAdapter

        val swl1 : SwipeRefreshLayout = findViewById(R.id.swl1)
        swl1.setOnRefreshListener {
            imageAdapter.refresh()
        }

        lifecycleScope.launch {
            viewModel.imageFlow.collectLatest { pagingData ->
                imageAdapter.submitData(pagingData)
            }
        }

        imageAdapter.addLoadStateListener { loadState ->
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
