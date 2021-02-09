package com.daeseong.texttospeech_test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var btn1: Button? = null

    private var textToSpeechUtil: TextToSpeechUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeechUtil = TextToSpeechUtil(this)

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener(View.OnClickListener {

            if( textToSpeechUtil!!.isLanguageAvailable() ) {
                textToSpeechUtil!!.Speak("가나다라마바사아자차타파하")
            } else {
                Toast.makeText(this, "한글을 사용할수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
