package com.daeseong.soaptest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.util.SoapXml

class MainActivity : AppCompatActivity() {

    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView1 = findViewById(R.id.textView1)

        val soapXml = SoapXml(textView1)
        try {
            soapXml.execute("검색어") // "검색어"는 실제 검색어로 교체
        } catch (e: Exception) {
            textView1.text = "Error"
            e.printStackTrace()
        }
    }
}
