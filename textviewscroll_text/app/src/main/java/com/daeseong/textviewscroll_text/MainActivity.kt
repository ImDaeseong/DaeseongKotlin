package com.daeseong.textviewscroll_text

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, TextViewScroll1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, TextViewScroll2Activity::class.java)
            startActivity(intent)
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, TextViewScroll3Activity::class.java)
            startActivity(intent)
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this@MainActivity, TextViewScroll4Activity::class.java)
            startActivity(intent)
        }

        button5 = findViewById(R.id.button5)
        button5.setOnClickListener {
            val intent = Intent(this@MainActivity, TextViewScroll5Activity::class.java)
            startActivity(intent)
        }

        button6 = findViewById(R.id.button6)
        button6.setOnClickListener {
            val intent = Intent(this@MainActivity, TextViewScroll6Activity::class.java)
            startActivity(intent)
        }

        button7 = findViewById(R.id.button7)
        button7.setOnClickListener {
            val intent = Intent(this@MainActivity, TextViewScroll7Activity::class.java)
            startActivity(intent)
        }


        //데이터
        UrlApi.getInstance().apply {
            setItem("텍스트1", "http://example1.com")
            setItem("텍스트2", "http://example2.com")
            setItem("텍스트3", "http://example3.com")
            setItem("텍스트4", "http://example4.com")
            setItem("텍스트5", "http://example5.com")
        }

        val allItems = UrlApi.getInstance().getItems()
        allItems.forEachIndexed { index, item ->
            println("Item ${index + 1}: ${item.getText()} - ${item.getUrl()}")
        }

    }
}