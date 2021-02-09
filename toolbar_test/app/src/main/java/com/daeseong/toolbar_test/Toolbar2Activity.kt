package com.daeseong.toolbar_test

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView

class Toolbar2Activity : AppCompatActivity() {

    private val tag: String = Toolbar2Activity::class.java.simpleName

    private var textView1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_toolbar2)

        textView1 = findViewById<TextView>(R.id.textview1)
        textView1!!.setText("<ScrollView\n" +
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
                "    </ScrollView>")
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
