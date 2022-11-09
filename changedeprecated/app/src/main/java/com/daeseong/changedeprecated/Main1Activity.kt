package com.daeseong.changedeprecated

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        initResult()

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@Main1Activity, Main1Activity_Sub::class.java)
            activityResultLauncher.launch(intent)
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

    private fun initResult() {

        //startActivityForResult 변경
        activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.data != null && result.resultCode == RESULT_OK) {

                val data1 = result.data!!.getStringExtra("string")
                val data2 = result.data!!.getIntExtra("int", 0)
                val data3 = result.data!!.getBooleanExtra("boolean", false)

                Log.e(tag, data1!!)
                Log.e(tag, data2.toString())
                Log.e(tag, data3.toString())
            }
        }
    }
}