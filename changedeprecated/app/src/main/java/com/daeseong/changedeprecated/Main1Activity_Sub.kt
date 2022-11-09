package com.daeseong.changedeprecated

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Main1Activity_Sub : AppCompatActivity() {

    private val tag = Main1Activity_Sub::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1_sub)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@Main1Activity_Sub, Main1Activity::class.java)
            intent.putExtra("string", "문자열")
            intent.putExtra("int", 1)
            intent.putExtra("boolean", true)
            setResult(RESULT_OK, intent)
            finish()
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            reStart()
        })
    }

    //앱 완전히 종료후 재시작
    private fun reStart() {
        val packageManager = packageManager
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = intent!!.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        exitProcess(0)
    }
}