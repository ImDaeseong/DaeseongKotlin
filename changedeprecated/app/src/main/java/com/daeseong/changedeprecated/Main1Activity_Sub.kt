package com.daeseong.changedeprecated

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main1Activity_Sub : AppCompatActivity() {

    private val tag = Main1Activity_Sub::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1_sub)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        button1.setOnClickListener {
            val intent = Intent(this@Main1Activity_Sub, Main1Activity::class.java).apply {
                putExtra("string", "문자열")
                putExtra("int", 1)
                putExtra("boolean", true)
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        button2.setOnClickListener {
            reStart()
        }
    }

    //앱 완전히 종료후 재시작
    private fun reStart() {
        val packageManager = packageManager
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = intent!!.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        finish()
    }
}