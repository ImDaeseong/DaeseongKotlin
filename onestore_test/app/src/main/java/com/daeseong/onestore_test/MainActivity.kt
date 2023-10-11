package com.daeseong.onestore_test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val sUrl = "https://"
            val downloadJson = DownloadJson()
            downloadJson.setJsonListener(object : DownloadJson.JsonListener {
                override fun onResult(sResult: String?) {
                    if (sResult.isNullOrEmpty()) {
                        Log.e(tag, "sResult: error")
                    } else {
                        Log.e(tag, "sResult: $sResult")
                    }
                }
            })
            downloadJson.execute(sUrl)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            //앱 설치 및 리뷰
            val sOneProduct = "onestore://common/product/"
            val sPackageId = "0000207200"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$sOneProduct$sPackageId"))

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "원스토어가 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            try {
                val sWebUrl = "https://m.onestore.co.kr/mobilepoc/apps/appsDetail.omp?prodId="
                val sPackageId = "0000207200"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$sWebUrl$sPackageId"))
                startActivity(intent)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            //앱버전
            val sPackageId = "com.kakao.talk"
            val oneStoreJson = OneStoreJson()
            oneStoreJson.setJsonListener(object : OneStoreJson.JsonListener {
                override fun onResult(sResult: String?) {
                    if (sResult.isNullOrEmpty()) {
                        Log.e(tag, "sResult: error")
                    } else {
                        try {
                            val resultObj = JSONObject(sResult)
                            var sprodId = resultObj.optString("prodId", "")
                            var sverNm = resultObj.optString("verNm", "")
                            var sverCd = resultObj.optString("verCd", "")
                            var result = resultObj.optString("result", "")
                            var stitle = resultObj.optString("title", "")
                            var ssellerNm = resultObj.optString("sellerNm", "")

                            Log.e(tag, "앱 ID :$sprodId")
                            Log.e(tag, "앱 Version :$sverNm")
                            Log.e(tag, "sverCd :$sverCd")
                            Log.e(tag, "result :$result")
                            Log.e(tag, "stitle :$stitle")
                            Log.e(tag, "ssellerNm :$ssellerNm")

                            val jsonObject = JSONObject(result)
                            if (jsonObject.getString("code") == "000" && jsonObject.getString("desc") == "성공") {
                                Log.e(tag, "success")
                            }
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                    }
                }
            })
            oneStoreJson.execute(sPackageId)
        }

    }
}