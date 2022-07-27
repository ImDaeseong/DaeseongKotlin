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

    private var sText : String? = ""
    private var nCount : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine3)

        textView1 = findViewById<TextView>(R.id.textView1)
        button1 = findViewById<Button>(R.id.button1)
        button2 = findViewById<Button>(R.id.button2)
        button3 = findViewById<Button>(R.id.button3)
        button4 = findViewById<Button>(R.id.button4)

        button1.setOnClickListener {

            textView1.text = ""
            normalFun()
        }

        button2.setOnClickListener {

            textView1.text = ""
            asyncFun()
        }

        button3.setOnClickListener {

            textView1.text = ""
            repeatFun()
        }

        button4.setOnClickListener {

            textView1.text = ""
            withTimeoutFun()
        }
    }

    private fun normalFun(){

        CoroutineScope(Dispatchers.IO).launch {

            val result1 = getDelay1()
            val result2 = getDelay2()

            sText += result1 + "\r\n"
            sText += result2

            CoroutineScope(Dispatchers.Main).launch {
                textView1.text = sText
            }
        }
    }

    private fun asyncFun(){

        CoroutineScope(Dispatchers.IO).launch {

            //async 비동기
            val result1 = async {
                getDelay1()
            }

            val result2 = async {
                getDelay2()
            }

            sText += result1.await() + "\r\n"
            sText += result2.await()

            CoroutineScope(Dispatchers.Main).launch {
                textView1.text = sText
            }
        }
    }

    private fun repeatFun() {

        CoroutineScope(Dispatchers.IO).launch {

            //repeat 회수만큼 반복
            repeat(5) {

                nCount ++
                //Log.e(tag, nCount.toString())

                val result1 = getDelay1()
                val result2 = getDelay2()

                sText += result1 + "\r\n"

                sText += if(nCount > 4)
                    result2
                else
                    result2 + "\r\n"

                CoroutineScope(Dispatchers.Main).launch {
                    textView1.text = sText
                }

                delay(1000)
            }
        }
    }

    private fun withTimeoutFun() {

        CoroutineScope(Dispatchers.IO).launch {

            try {

                withTimeout(5000) {

                    repeat(5000){

                        nCount ++
                        //Log.e(tag, nCount.toString())

                        val result1 = getDelay1()

                        sText += result1 + "\r\n"

                        sText += if(nCount > 4)
                            result1
                        else
                            result1 + "\r\n"

                        CoroutineScope(Dispatchers.Main).launch {
                            textView1.text = sText
                        }

                        delay(100)
                    }
                }

            }catch (e : TimeoutCancellationException){
                Log.e(tag, e.message.toString())
            }
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
}