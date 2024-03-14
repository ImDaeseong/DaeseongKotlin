package com.daeseong.permission33sdk_test

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class Permission3Activity : AppCompatActivity() {

    private val tag = Permission3Activity::class.simpleName

    private lateinit var button1: Button
    private lateinit var permissResultLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission3)

        initResultPermission()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    permissResultLauncher.launch(POST_NOTIFICATIONS)
                } else {
                    Log.e(tag, "POST_NOTIFICATIONS 권한 소유")
                }

            } else {
                Log.e(tag, "sdk 33 이하")
            }
        }

    }

    private fun initResultPermission() {
        permissResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                Log.e(tag, "POST_NOTIFICATIONS 권한 소유")
            } else {
                Log.e(tag, "POST_NOTIFICATIONS 권한 미소유")
            }
        }
    }
}