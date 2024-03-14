package com.daeseong.permission33sdk_test

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class Permission8Activity : AppCompatActivity() {

    private val tag = Permission8Activity::class.simpleName

    private lateinit var button1: Button

    private lateinit var permissResultLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission8)

        initPermissionsLauncher()

        init()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            finish()
        }
    }

    private fun initPermissionsLauncher() {

        permissResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->

            if (result) {
                Log.e(tag, "POST_NOTIFICATIONS 권한 소유")
            } else {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {

                    Log.e(tag, "앱 설정창에서 권한 체크 해야함")

                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.fromParts("package", packageName, null)
                    startActivity(intent)

                    // 전체 목록 호출
                    //startActivity(Intent(Settings.ACTION_SETTINGS))

                } else {
                    Log.e(tag, "POST_NOTIFICATIONS 권한 체크 거부")
                }
            }
        }
    }

    private fun init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                Log.e(tag, "POST_NOTIFICATIONS 권한 소유")
            }

        } else {
            Log.e(tag, "sdk 33 이하")
        }
    }
}