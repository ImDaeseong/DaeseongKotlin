package com.im.daeseong.leakcanary_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LeakTestActivity : AppCompatActivity() {

    private val tag = LeakTestActivity::class.java.simpleName

    private lateinit var button1: Button

    // 정적(static) 참조를 만들어 확실한 메모리 누수를 발생시킴
    companion object {
        private var leakyContext: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //시스템 바가 UI에 영향을 주지 않도록 조정하여, 앱의 화면이 끝까지 표시
        enableEdgeToEdge()
        setContentView(R.layout.activity_leak_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            finish()
        }

        // 의도적으로 메모리 누수 생성 - Activity의 참조를 정적 변수에 저장
        leakyContext = this;

        Log.e(tag, "메모리 누수 테스트: Activity 참조가 정적 변수에 저장됨")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e(tag, "액티비티 소멸됨, 하지만 leakyContext에 여전히 참조가 남아있음")
    }
}