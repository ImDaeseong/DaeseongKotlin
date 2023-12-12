package com.daeseong.scheme_test

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val uri: Uri? = intent.data
        if (uri != null) {
            val scheme: String? = uri.scheme
            val param1: String? = uri.getQueryParameter("param1")
            val param2: String? = uri.getQueryParameter("param2")
            Toast.makeText(this, "$scheme param1: $param1, param2: $param2", Toast.LENGTH_SHORT).show()
        }
    }
}