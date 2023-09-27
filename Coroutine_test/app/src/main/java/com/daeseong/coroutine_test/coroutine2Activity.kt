package com.daeseong.coroutine_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class coroutine2Activity : AppCompatActivity() {

    private val tag: String = coroutine2Activity::class.java.simpleName

    private lateinit var textView1: TextView
    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine2)

        textView1 = findViewById(R.id.textView1)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {

            // Dispatchers.Default는 CPU를 많이 사용하는 작업을 기본 스레드 외부에서 실행하도록 최적화되어 있습니다.
            CoroutineScope(Dispatchers.Main).launch {
                counterTime()
            }
        }
    }

    private suspend fun counterTime() {
        for (i in 1..100000) {

            //textView1의 내용을 백그라운드 스레드에서 업데이트
            withContext(Dispatchers.Main) {
                textView1.text = i.toString()
            }

            //딜레이를 주어 UI를 업데이트
            delay(1)
        }
    }
}