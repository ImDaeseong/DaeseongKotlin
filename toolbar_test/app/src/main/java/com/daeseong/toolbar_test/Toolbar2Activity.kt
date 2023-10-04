package com.daeseong.toolbar_test

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView

class Toolbar2Activity : AppCompatActivity() {

    private val tag: String = Toolbar2Activity::class.java.simpleName

    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_toolbar2)

        textView1 = findViewById(R.id.textview1)
        textView1.text = "<ScrollView\n" +
                "        android:id=\"@+id/scrollviewTextarea\"\n" +
                "        android:layout_width=\"fill_parent\"\n" +
                "        android:layout_height=\"fill_parent\"\n" +
                "        android:scrollbarFadeDuration=\"500\"\n" +
                "        android:scrollbars=\"vertical\" >\n" +
                "\n" +
                "        <TextView\n" +
                "            android:id=\"@+id/textviewScroll\"\n" +
                "            android:layout_width=\"fill_parent\"\n" +
                "            android:layout_height=\"fill_parent\"\n" +
                "            android:padding=\"10dp\"\n" +
                "            android:textSize=\"16dp\" />\n" +
                "\n" +
                "    </ScrollView>" +
                "<ScrollView\n" +
                "        android:id=\"@+id/scrollviewTextarea\"\n" +
                "        android:layout_width=\"fill_parent\"\n" +
                "        android:layout_height=\"fill_parent\"\n" +
                "        android:scrollbarFadeDuration=\"500\"\n" +
                "        android:scrollbars=\"vertical\" >\n" +
                "\n" +
                "        <TextView\n" +
                "            android:id=\"@+id/textviewScroll\"\n" +
                "            android:layout_width=\"fill_parent\"\n" +
                "            android:layout_height=\"fill_parent\"\n" +
                "            android:padding=\"10dp\"\n" +
                "            android:textSize=\"16dp\" />\n" +
                "\n" +
                "    </ScrollView>" +
                "<ScrollView\n" +
                "        android:id=\"@+id/scrollviewTextarea\"\n" +
                "        android:layout_width=\"fill_parent\"\n" +
                "        android:layout_height=\"fill_parent\"\n" +
                "        android:scrollbarFadeDuration=\"500\"\n" +
                "        android:scrollbars=\"vertical\" >\n" +
                "\n" +
                "        <TextView\n" +
                "            android:id=\"@+id/textviewScroll\"\n" +
                "            android:layout_width=\"fill_parent\"\n" +
                "            android:layout_height=\"fill_parent\"\n" +
                "            android:padding=\"10dp\"\n" +
                "            android:textSize=\"16dp\" />\n" +
                "\n" +
                "    </ScrollView>"
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        try {
            //안드로이드 8.0 오레오 버전에서만 오류 발생
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        } catch (ex: Exception) {
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}
