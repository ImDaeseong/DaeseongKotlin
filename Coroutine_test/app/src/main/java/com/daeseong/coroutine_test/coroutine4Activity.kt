package com.daeseong.coroutine_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class coroutine4Activity : AppCompatActivity() {

    private val tag: String = coroutine4Activity::class.java.simpleName

    private lateinit var textView1: TextView
    private lateinit var button1: Button

    private var nCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine4)

        textView1 = findViewById(R.id.textView1)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {

            // UI 업데이트를 위한 메인 스레드에서의 CoroutineScope 생성
            CoroutineScope(Dispatchers.Main).launch {

                // Job을 생성하여 취소할 수 있는 작업 수행
                val job = launch(Dispatchers.Default) {

                    repeat(5) {

                        nCount++
                        Log.e(tag, nCount.toString())

                        // UI 업데이트
                        withContext(Dispatchers.Main) {
                            textView1.text = nCount.toString()
                        }

                        delay(1000)
                    }
                }

                // 2초 후 job을 취소하고 종료를 대기
                delay(2000)
                job.cancelAndJoin()

                Log.e(tag, "job.cancelAndJoin()")
            }
        }

    }
}