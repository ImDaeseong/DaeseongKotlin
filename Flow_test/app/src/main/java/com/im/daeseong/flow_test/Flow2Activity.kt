package com.im.daeseong.flow_test

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Flow2Activity : AppCompatActivity() {

    // OkHttpClient 재사용을 위한 싱글톤
    object HttpClient {
        val client = OkHttpClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_flow2)

        // 시스템 바 인셋 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tv1 = findViewById<TextView>(R.id.tv1)

        //lifecycleScope 는 액티비티 종료시 자동 종료
        lifecycleScope.launch {

            getPages(1, 3).collect { data ->
                tv1.append(data)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // lifecycleScope에서 자동으로 취소됨
    }

    fun getPages(sStart: Int, sEnd: Int): Flow<String> = flow {

        for (page in sStart..sEnd) {

            val data = withContext(Dispatchers.IO) {
                getData("https://api.github.com/search/repositories?sort=stars&per_page=10&q=android&page=$page")
            }
            emit("Page $page:\n$data\n\n")

            delay(1000)
        }
    }

    private fun getData(sUrl: String): String {

        val request = Request.Builder()
            .url(sUrl)
            .build()

        return try {

            val response: Response = HttpClient.client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string() ?: ""
            } else {
                ""
            }

        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }
}