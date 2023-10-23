package com.daeseong.kakao_test

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getHashKey()

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            val intent = Intent(this, Main1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
    }

    //카카오앱 만들시 필요
    private fun getHashKey() {

        var packageInfo: PackageInfo? = null

        try {

            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (ex: PackageManager.NameNotFoundException) {
            Log.e(tag, ex.message.toString())
        }

        for (signature in packageInfo!!.signatures) {

            try {

                val messageDigest: MessageDigest = MessageDigest.getInstance("SHA")
                messageDigest.update(signature.toByteArray())
                Log.d(tag, "haskey:" + Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT))
            } catch (ex: NoSuchAlgorithmException) {
                Log.e(tag, ex.message.toString())
            }
        }

    }
}
