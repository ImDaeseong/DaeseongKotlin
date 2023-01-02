package com.daeseong.paging_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.paging_test.Common.DateTime
import com.daeseong.paging_test.Common.HttpUtilOK.getData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class Main2Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var button1: Button? = null

    private var sResult = ""

    private var sUrl = ""
    private val sSearchkey = "android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tv1 = findViewById<View>(R.id.tv1) as TextView
        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            try {

                sUrl = String.format("%s&q=%s:created:>%s", ConstantsUrl.sUrl1, sSearchkey, DateTime.getOneDayago() )
                getData(this@Main2Activity, sUrl)

            } catch (ex: java.lang.Exception) {
                Log.e(tag, ex.message.toString())
            }
        }
    }

    private fun getData(context: Context, sUrl: String) {

        getData(sUrl, object : Callback {

            override fun onFailure(call: Call, e: IOException) {

                Log.e(tag, "getData onFailure")
            }

            override fun onResponse(call: Call, response: Response) {

                try {
                    sResult = response.body().string()
                    runOnUiThread { tv1!!.text = sResult }
                } catch (ex: Exception) {
                    Log.e(tag, ex.message.toString())
                }

            }
        })

    }

}