package com.im.daeseong.flow_test

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class Flow3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_flow3)

        // 시스템 바 인셋 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tv1 = findViewById<TextView>(R.id.tv1)

        //lifecycleScope 는 액티비티 종료시 자동 종료
        lifecycleScope.launch {

            try {

                val data = getPages(1, 3)
                withContext(Dispatchers.Main) {
                    tv1.text = data
                }

            } catch (e:Exception) {
                withContext(Dispatchers.Main) {
                    tv1.text = ""
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // lifecycleScope에서 자동으로 취소됨
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

    suspend fun getData(sUrl: String): String {

        return suspendCancellableCoroutine { continuation ->

            OkHttpUtil.getDataResult(sUrl, object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val result = response.body?.string() ?: ""
                        continuation.resume(result)
                    } else {
                        continuation.resumeWithException(Exception(""))
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }
            })
        }
    }

}