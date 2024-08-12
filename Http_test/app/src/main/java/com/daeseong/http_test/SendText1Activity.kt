package com.daeseong.http_test

import SendMessage
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class SendText1Activity : AppCompatActivity() {

    private val tag: String = SendText1Activity::class.java.simpleName

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var button1: Button
    private lateinit var textView5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_text1)

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        button1 = findViewById(R.id.button1)
        textView5 = findViewById(R.id.textView5)

        button1.setOnClickListener {

            val sText1 = editText1.text.toString()
            val sText2 = editText2.text.toString()
            val sText3 = editText3.text.toString()
            val postParams = "USERNO=$sText1&NAME=$sText2&PHONE=$sText3"
            val url1 = "https://"

            lifecycleScope.launch {
                val sendMessage = SendMessage(this@SendText1Activity, textView5)
                sendMessage.sendPostRequest(url1, postParams)
            }
        }
    }

    private fun isConnection(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}
