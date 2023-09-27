package com.daeseong.changedeprecated

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        initResult()

        button1.setOnClickListener {
            val intent = Intent(this@Main1Activity, Main1Activity_Sub::class.java)
            activityResultLauncher.launch(intent)
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
        exitProcess(0)
    }

    private fun initResult() {

        //startActivityForResult 변경
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

            if (result.resultCode == RESULT_OK) {
                val data1 = result.data?.getStringExtra("string")
                val data2 = result.data?.getIntExtra("int", 0)
                val data3 = result.data?.getBooleanExtra("boolean", false)

                Log.e(tag, data1 ?: "Data1 is null")
                Log.e(tag, data2.toString())
                Log.e(tag, data3.toString())
            }
        }
    }
}