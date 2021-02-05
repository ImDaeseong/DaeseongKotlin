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

    private val tag: String = MainActivity::class.java.simpleName;

    private var button1 : Button? = null;
    private var button2 : Button? = null;
    private var button3 : Button? = null;
    private var button4 : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(this);

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener(this);

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(this);

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener(this);
    }

    override fun onClick(v: View?) {

        if (v != null) {

            when (v.id) {

                R.id.button1 -> {

                    val intent = Intent(this, Main1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button2 -> {

                    val intent = Intent(this, Main2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button3 -> {

                    val intent = Intent(this, Main3Activity::class.java)
                    startActivity(intent)
                }

                R.id.button4 -> {

                    val intent = Intent(this, Main4Activity::class.java)
                    startActivity(intent)
                }
            }
        }

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
