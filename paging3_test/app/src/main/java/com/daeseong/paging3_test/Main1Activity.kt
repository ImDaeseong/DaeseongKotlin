package com.daeseong.paging3_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daeseong.paging3_test.fun1.*
import kotlinx.coroutines.flow.collectLatest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main1Activity : AppCompatActivity() {

    private companion object {
        private val tag = Main1Activity::class.java.name
    }

    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val recyclerView: RecyclerView = findViewById(R.id.rv1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter()
        recyclerView.adapter = adapter


        val swl1 : SwipeRefreshLayout = findViewById(R.id.swl1)
        swl1.setOnRefreshListener {
            adapter.refresh()
        }

        val apiService = createApiService()
        viewModel = ViewModelProvider(this, ViewModelFactory(apiService)).get(ItemViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            viewModel.getItem("android").collectLatest {
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

    private fun createApiService(): GetApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        return retrofit.create(GetApiService::class.java)
    }

}
