package com.daeseong.coroutine_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class coroutine3Activity : AppCompatActivity() {

    private val tag: String = coroutine3Activity::class.java.simpleName

    private lateinit var textView1: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private var sText: String = ""
    private var nCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine3)

        textView1 = findViewById(R.id.textView1)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener {
            textView1.text = ""
            CoroutineScope(Dispatchers.IO).launch {
                normalFun()
            }
        }

        button2.setOnClickListener {
            textView1.text = ""
            CoroutineScope(Dispatchers.IO).launch {
                asyncFun()
            }
        }

        button3.setOnClickListener {
            textView1.text = ""
            CoroutineScope(Dispatchers.IO).launch {
                repeatFun()
            }
        }

        button4.setOnClickListener {
            textView1.text = ""
            CoroutineScope(Dispatchers.IO).launch {
                withTimeoutFun()
            }
        }
    }

    private suspend fun normalFun() {
        val result1 = getDelay1()
        val result2 = getDelay2()
        sText += "$result1\r\n$result2"
        updateTextView()
    }

    private suspend fun asyncFun() {

        val result1 = getDelay1()
        val result2 = getDelay2()
        val combinedResult = "$result1\r\n$result2"

        // 결과를 UI 스레드에서 표시
        withContext(Dispatchers.Main) {
            sText += combinedResult
            updateTextView()
        }
    }

    private suspend fun repeatFun() {
        repeat(5) {
            nCount++
            val result1 = getDelay1()
            val result2 = getDelay2()
            sText += "$result1\r\n"
            sText += if (nCount > 4) result2 else "$result2\r\n"
            updateTextView()
            delay(1000)
        }
    }

    private suspend fun withTimeoutFun() {
        try {
            withTimeout(5000) {
                repeat(5000) {
                    nCount++
                    val result1 = getDelay1()
                    sText += "$result1\r\n"
                    sText += if (nCount > 4) result1 else "$result1\r\n"
                    updateTextView()
                    delay(100)
                }
            }
        } catch (e: TimeoutCancellationException) {
            Log.e(tag, e.message.toString())
        }
    }

    private suspend fun getDelay1(): String {
        delay(1000)
        return "getDelay1"
    }

    private suspend fun getDelay2(): String {
        delay(3000)
        return "getDelay2"
    }

    private suspend fun updateTextView() {
        withContext(Dispatchers.Main) {
            textView1.text = sText
        }
    }
}