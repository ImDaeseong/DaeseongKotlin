package com.daeseong.textviewscroll_text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TextViewScroll7Activity : AppCompatActivity() {

    companion object {
        private val tag = TextViewScroll7Activity::class.java.simpleName
    }

    private lateinit var cL1: View
    private lateinit var cL2: View
    private lateinit var textScrollerCL: TextScrollerCL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_scroll7)

        cL1 = findViewById(R.id.cL1)
        cL2 = findViewById(R.id.cL2)

        // 스크롤 클래스
        textScrollerCL = TextScrollerCL(cL1, cL2)
        //textScrollerCL.setDirect(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        textScrollerCL.stop()
    }

    override fun onPause() {
        super.onPause()
        textScrollerCL.stop()
    }

    override fun onResume() {
        super.onResume()
        textScrollerCL.start()
    }
}