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

    private lateinit var job: Job
    private var nCount : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine4)

        textView1 = findViewById<TextView>(R.id.textView1)
        button1 = findViewById<Button>(R.id.button1)

        button1.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {

                job = CoroutineScope(Dispatchers.Main).launch {

                    repeat(5) {

                        nCount ++
                        Log.e(tag, nCount.toString())
                        delay(1000)
                    }
                }

                //2초후 job 종료
                delay(2000)
                job.cancelAndJoin()

                Log.e(tag, "job.cancelAndJoin()")
            }
        }
    }
}