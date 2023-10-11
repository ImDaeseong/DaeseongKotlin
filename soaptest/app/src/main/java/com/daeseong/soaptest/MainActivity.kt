package com.daeseong.soaptest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.util.SoapXml

class MainActivity : AppCompatActivity() {

    private var soapXml: SoapXml? = null
    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView1 = findViewById(R.id.textView1)

        try {
            soapXml = SoapXml(textView1)
            soapXml?.execute("검색어")
        } catch (e: Exception) {
            soapXml?.cancel(true)
            textView1.text = "Error"
            e.printStackTrace()
        }
    }
}