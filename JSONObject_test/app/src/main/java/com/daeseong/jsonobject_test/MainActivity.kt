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

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            try {

                val jsonWrite = JSONObject().apply {
                    put("index", 1)
                    put("id", "com.netmarble.lineageII")
                    put("title", "리니지2 레볼루션")
                }

                with(jsonWrite) {
                    if (has("index")) {
                        val indexValue = getInt("index")
                        Log.e(tag, "index:$indexValue")
                    }

                    if (has("id")) {
                        Log.e(tag, "id:" + getString("id"))
                    }

                    if (has("title")) {
                        Log.e(tag, "title:" + getString("title"))
                    }
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {

            try {

                val jsonWrite = JSONObject().apply {
                    val jsonArray = JSONArray().apply {
                        for (i in 0..1) {
                            put("title$i")
                        }
                    }
                    put("ary", jsonArray)
                }

                val arykey = JSONObject(jsonWrite.toString())
                if (arykey.has("ary")) {
                    val ary = arykey.getJSONArray("ary")
                    for (i in 0 until ary.length()) {
                        Log.e(tag, "ary value:${ary[i]}")
                    }
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {

            try {

                val jsonWrite = JSONObject().apply {
                    val jsonArray = JSONArray().apply {
                        for (i in 0..1) {
                            val jsonObject = JSONObject().apply {
                                put("index", i + 1)
                                put("id", "com.netmarble.lineageII")
                                put("title", "리니지2 레볼루션")
                            }
                            put(jsonObject)
                        }
                    }
                    put("ary", jsonArray)
                }

                val arykey = JSONObject(jsonWrite.toString())
                if (arykey.has("ary")) {
                    val ary = arykey.getJSONArray("ary")
                    for (i in 0 until ary.length()) {
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
        }
    }
}