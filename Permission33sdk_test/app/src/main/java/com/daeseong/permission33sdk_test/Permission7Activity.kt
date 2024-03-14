package com.daeseong.permission33sdk_test

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Permission7Activity : AppCompatActivity() {

    private val tag = Permission7Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var requestPermissions: ActivityResultLauncher<Array<String>>

    /*
    [일회성 권한]
    안드로이드 11 이상 : 위치, 카메라 접근권한 요청 시 이번만 허용 옵션 추가
    안드로이드 10 이하 : 이번만 허용 옵션 없음
    */
    //안드로이드 11 이상부터는 허용, 이번만 허용, 허용 안됨
    //안드로이드 10 이하는 허용, 거부

    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission7)

        init()

        initPermissionsLauncher()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            var result = false
            for (permission in PERMISSIONS) {
                result = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
                if (!result) {
                    break
                }
            }

            if (result) {
                Toast.makeText(this@Permission7Activity, "이미 권한 소유", Toast.LENGTH_SHORT).show()
            } else {
                requestPermissions.launch(PERMISSIONS)
            }

        }
    }

    private fun initPermissionsLauncher() {
        requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            Toast.makeText(this@Permission7Activity, result.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {
        for (permission in PERMISSIONS) {

            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this@Permission7Activity, "$permission 미허용 상태", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@Permission7Activity, "$permission 허용 상태", Toast.LENGTH_SHORT).show()
            }
        }
    }
}