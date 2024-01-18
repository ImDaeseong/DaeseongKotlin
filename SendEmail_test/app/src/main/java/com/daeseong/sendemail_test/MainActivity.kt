package com.daeseong.sendemail_test

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private val sAddresses = arrayOf("cs93059@gmail.com")

    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var btn1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {
            val title = et1.text.toString()
            val content = et2.text.toString()

            // 이메일 보내기 Intent 생성
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, sAddresses)
            intent.putExtra(Intent.EXTRA_SUBJECT, title)
            intent.putExtra(Intent.EXTRA_TEXT, content)

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

            // 이메일 보내기
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Log.e(tag, "메일 보낼수 있는 앱이 없습니다.")
            }

            et1.setText("")
            et2.setText("")
            et1.requestFocus()
        }
    }
}