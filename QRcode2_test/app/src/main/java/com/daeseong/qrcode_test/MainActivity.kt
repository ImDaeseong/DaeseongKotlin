package com.daeseong.qrcode_test

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.button1 -> callActivity<Main1Activity>()
                R.id.button2 -> callActivity<Main2Activity>()
                R.id.button3 -> callActivity<Main3Activity>()
                R.id.button4 -> callActivity<Main4Activity>()
                R.id.button5 -> callActivity<Main5Activity>()
            }
        }
    }

    private inline fun <reified T : AppCompatActivity> callActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

    private fun checkPermission(): Boolean {

        //마시멜로 이상일 경우만 권한 체크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //권한이 없는 경우 요청
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

            if (grantResults.isNotEmpty()) {

                val result = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (!result) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                        }
                    }
                }

            }
        }

    }

}
