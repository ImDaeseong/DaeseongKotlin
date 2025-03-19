package com.im.daeseong.flow_test

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Flow1Activity : AppCompatActivity() {

    // OkHttpClient 재사용을 위한 싱글톤
    object HttpClient {
        val client = OkHttpClient()
    }

    private val coroutine = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_flow1)

        // 시스템 바 인셋 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tv1 = findViewById<TextView>(R.id.tv1)

        //CoroutineScope 앱이 종료되지 전까지는 유지
        coroutine.launch {

            val data = getPages(1, 3)

            withContext(Dispatchers.Main) {
                tv1.text = data
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // CoroutineScope 종료 시 취소
        coroutine.cancel()
    }

    suspend fun getPages(sStart: Int, sEnd: Int): String {

        val results = StringBuilder()

        // sStart 부터 sEnd 까지
        for (page in sStart..sEnd) {

            val data = getData("https://api.github.com/search/repositories?sort=stars&per_page=10&q=android&page=$page")
            results.append("Page $page:\n$data\n\n")

            delay(1000)
        }

        return results.toString()
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