package com.daeseong.texttospeech_test

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var btn1: Button
    private lateinit var textToSpeechUtil: TextToSpeechUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeechUtil = TextToSpeechUtil(this)

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            if (textToSpeechUtil.isLanguageAvailable()) {
                textToSpeechUtil.speak("가나다라마바사아자차타파하")
            } else {
                showToast("한글을 사용할 수 없습니다.")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
