package com.im.daeseong.permission_test.Util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUtil {

    const val RESULT_CODE = 1
    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE
    )

    // 권한 체크
    fun checkSelfPermission(activity: Activity, permission: String): Boolean {
        val result = ActivityCompat.checkSelfPermission(activity, permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    // 권한 요청
    fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(activity, PERMISSIONS, RESULT_CODE)
    }

    // 권한 요청에 대한 승인 확인
    fun checkGranted(results: IntArray): Boolean {
        if (results.isEmpty()) {
            return false
        }

        for (result in results) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}
