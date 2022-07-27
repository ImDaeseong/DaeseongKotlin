package com.daeseong.coroutine_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class coroutine2Activity : AppCompatActivity() {

    private val tag: String = coroutine2Activity::class.java.simpleName

    private lateinit var textView1: TextView
    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine2)

        textView1 = findViewById<TextView>(R.id.textView1)
        button1 = findViewById<Button>(R.id.button1)

        button1.setOnClickListener {

            //Dispatchers.Default 디스패처는 CPU를 많이 사용하는 작업을 기본 스레드 외부에서 실행하도록 최적화되어 있습니다.
            CoroutineScope(Dispatchers.Default).launch {
                CounterTime()
            }
        }
    }

    private suspend fun CounterTime(){

        //withContext 는  async 와 동일한 역할
        for(i in 1..100000){
            withContext(Dispatchers.Main){
                textView1.text = i.toString()
            }
        }
    }
}