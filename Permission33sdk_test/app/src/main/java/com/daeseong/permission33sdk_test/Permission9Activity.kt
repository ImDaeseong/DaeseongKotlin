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
import androidx.core.content.ContextCompat

class Permission9Activity : AppCompatActivity() {

    private val tag = Permission9Activity::class.simpleName

    private lateinit var button1: Button

    private lateinit var permissResultLauncher: ActivityResultLauncher<Array<String>>

    private val PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private val PERMISSIONS33 = arrayOf(Manifest.permission.CAMERA,Manifest.permission.POST_NOTIFICATIONS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission9)

        initPermissionsLauncher()

        init()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            finish()
        }
    }

    private fun initPermissionsLauncher() {

        permissResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach { entry ->

                val permission = entry.key
                val isGranted = entry.value

                if (isGranted) {

                    Log.e(tag, "$permission 권한 소유")
                } else {

                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                        Log.e(tag, "앱 설정창에서 권한 체크 해야함")

                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.fromParts("package", packageName, null)
                        startActivity(intent)
                    } else {

                        Log.e(tag, "$permission 권한이 거부됨")
                    }
                }
            }
        }
    }

    private fun init() {

        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) PERMISSIONS33 else PERMISSIONS

        permissions.forEach { permission ->

            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {

                Log.e(tag, "$permission 권한 없음")
                permissResultLauncher.launch(arrayOf(permission))
            } else {

                Log.e(tag, "권한 이미 있음")
            }
        }
    }
}