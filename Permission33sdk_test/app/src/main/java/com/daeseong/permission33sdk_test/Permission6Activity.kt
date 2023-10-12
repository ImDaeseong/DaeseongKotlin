package com.daeseong.permission33sdk_test

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class Permission6Activity : AppCompatActivity() {

    private val TAG = Permission6Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var requestPermissions: ActivityResultLauncher<Array<String>>

    private val PERMISSIONS = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val PERMISSIONS33 = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.POST_NOTIFICATIONS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission6)

        initPermissionsLauncher()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            //Log.e(TAG, "필수 권한만 체크하고 선택 권한은 무시")

            var result = false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                result = checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED

            } else {

                for (permission in PERMISSIONS) {
                    result = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
                    if (!result) {
                        break
                    }
                }
            }

            if (result) {
                Log.e(TAG, "이미 권한 소유")
            } else {
                //Log.e(TAG, "권한 체크")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermissions.launch(PERMISSIONS33)
                } else {
                    requestPermissions.launch(PERMISSIONS)
                }
            }

        }
    }

    private fun initPermissionsLauncher() {
        requestPermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

                var bPermissions = false

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (result[Manifest.permission.READ_PHONE_STATE] == true) {
                        bPermissions = true
                    }
                } else {
                    if (result[Manifest.permission.READ_PHONE_STATE] == true && result[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true) {
                        bPermissions = true
                    }
                }

                if (bPermissions) {
                    Log.e(TAG, "전체 퍼미션 허용")
                } else {
                    Log.e(TAG, "퍼미션 요청 거절 상태")
                }
            }
    }
}