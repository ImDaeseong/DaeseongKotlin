package com.daeseong.jsonobject_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            try {

                val jsonWrite = JSONObject()
                jsonWrite.put("index", 1)
                jsonWrite.put("id", "com.netmarble.lineageII")
                jsonWrite.put("title", "리니지2 레볼루션")
                //Log.e(tag, "jsonWrite:$jsonWrite")

                if (jsonWrite.has("index")) {
                    Log.e(tag, "index:" + jsonWrite.getInt("index"))
                }

                if (jsonWrite.has("id")) {
                    Log.e(tag, "id:" + jsonWrite.getString("id"))
                }

                if (jsonWrite.has("title")) {
                    Log.e(tag, "title:" + jsonWrite.getString("title"))
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            try {

                //json array 생성
                val jsonWrite = JSONObject()
                val jsonArray = JSONArray()
                for (i in 0..1) {
                    jsonArray.put("title$i")
                }
                jsonWrite.put("ary", jsonArray)
                //Log.e(tag, "ary:$jsonWrite")

                //json array 조회
                val arykey = JSONObject(jsonWrite.toString())
                if (arykey.has("ary")) {
                    val ary = arykey.getJSONArray("ary")
                    for (i in 0 until ary.length()) {
                        Log.e(tag, "ary value:" + ary[i])
                    }
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        })

        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener(View.OnClickListener {

            try {

                //json array 생성
                val jsonWrite = JSONObject()
                val jsonArray = JSONArray()

                for (i in 0..1) {
                    val jsonObject = JSONObject()
                    jsonObject.put("index", i + 1)
                    jsonObject.put("id", "com.netmarble.lineageII")
                    jsonObject.put("title", "리니지2 레볼루션")
                    jsonArray.put(jsonObject)
                }
                jsonWrite.put("ary", jsonArray)
                //Log.e(tag, "ary:$jsonWrite")

                //json array 조회
                val arykey = JSONObject(jsonWrite.toString())
                if (arykey.has("ary")) {
                    val ary = arykey.getJSONArray("ary")
                    for (i in 0 until ary.length()) {
                        //Log.e(tag, "ary value:$ary.getJSONObject(i)")
                        val jsonObject = ary.getJSONObject(i)
                        val index = jsonObject.getString("index")
                        val id = jsonObject.getString("id")
                        val title = jsonObject.getString("title")
                        Log.e(tag, "index:$index id:$id title:$title")
                    }
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        })
    }

}