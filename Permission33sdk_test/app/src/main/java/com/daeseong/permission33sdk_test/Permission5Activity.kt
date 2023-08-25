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

class Permission5Activity : AppCompatActivity() {

    private val TAG = Permission5Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var requestPermissions: ActivityResultLauncher<Array<String>>

    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val PERMISSIONS33 = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_VIDEO,
        Manifest.permission.READ_MEDIA_AUDIO,
        Manifest.permission.POST_NOTIFICATIONS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission5)

        initPermissionsLauncher()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                checkAndRequestPermissions(PERMISSIONS33)
            } else {
                checkAndRequestPermissions(PERMISSIONS)
            }
        }
    }

    private fun checkAndRequestPermissions(permissions: Array<String>) {
        var allPermissionsGranted = true

        for (permission in permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false
                break
            }
        }

        if (!allPermissionsGranted) {
            requestPermissions.launch(permissions)
        } else {
            Log.e(TAG, "권한이 이미 허용되었습니다.")
        }
    }

    private fun initPermissionsLauncher() {

        requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

            try {
                for ((permission, isGranted) in result) {
                    when {

                        //첫번째 방식
                        /*
                        PERMISSIONS.contains(permission) -> {
                            if (isGranted) {
                                Log.e(TAG, "$permission 권한 허용")
                            } else {
                                Log.e(TAG, "$permission 권한 거부")
                            }
                        }
                        PERMISSIONS33.contains(permission) -> {
                            if (isGranted) {
                                Log.e(TAG, "$permission 권한 허용")
                            } else {
                                Log.e(TAG, "$permission 권한 거부")
                            }
                        }
                        */

                        Manifest.permission.CAMERA == permission -> {
                            if (isGranted) {
                                Log.e(TAG, "CAMERA 권한 허용")
                            } else {
                                Log.e(TAG, "CAMERA 권한 거부")
                            }
                        }
                        Manifest.permission.READ_EXTERNAL_STORAGE == permission -> {
                            if (isGranted) {
                                Log.e(TAG, "READ_EXTERNAL_STORAGE 권한 허용")
                            } else {
                                Log.e(TAG, "READ_EXTERNAL_STORAGE 권한 거부")
                            }
                        }
                        Manifest.permission.WRITE_EXTERNAL_STORAGE == permission -> {
                            if (isGranted) {
                                Log.e(TAG, "WRITE_EXTERNAL_STORAGE 권한 허용")
                            } else {
                                Log.e(TAG, "WRITE_EXTERNAL_STORAGE 권한 거부")
                            }
                        }
                        Manifest.permission.READ_MEDIA_IMAGES == permission -> {
                            if (isGranted) {
                                Log.e(TAG, "READ_MEDIA_IMAGES 권한 허용")
                            } else {
                                Log.e(TAG, "READ_MEDIA_IMAGES 권한 거부")
                            }
                        }
                        Manifest.permission.READ_MEDIA_VIDEO == permission -> {
                            if (isGranted) {
                                Log.e(TAG, "READ_MEDIA_VIDEO 권한 허용")
                            } else {
                                Log.e(TAG, "READ_MEDIA_VIDEO 권한 거부")
                            }
                        }
                        Manifest.permission.READ_MEDIA_AUDIO == permission -> {
                            if (isGranted) {
                                Log.e(TAG, "READ_MEDIA_AUDIO 권한 허용")
                            } else {
                                Log.e(TAG, "READ_MEDIA_AUDIO 권한 거부")
                            }
                        }
                        Manifest.permission.POST_NOTIFICATIONS == permission -> {
                            if (isGranted) {
                                Log.e(TAG, "POST_NOTIFICATIONS 권한 허용")
                            } else {
                                Log.e(TAG, "POST_NOTIFICATIONS 권한 거부")
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            /*
            try {
                for ((permission, isGranted) in result) {
                    when (permission) {
                        Manifest.permission.CAMERA -> {
                            if (isGranted) {
                                Log.e(TAG, "CAMERA 권한 허용")
                            } else {
                                Log.e(TAG, "CAMERA 권한 거부")
                            }
                        }
                        Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_EXTERNAL_STORAGE -> {
                            if (isGranted) {
                                Log.e(TAG, "이미지 읽기 권한 허용")
                            } else {
                                Log.e(TAG, "이미지 읽기 권한 거부")
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            */
        }
    }
}