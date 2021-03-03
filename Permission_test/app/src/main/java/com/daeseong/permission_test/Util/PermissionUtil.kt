package com.daeseong.permission_test.Util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


object PermissionUtil {

    const val RESULT_CODE = 1

    private val PERMISSIONS = arrayOf<String>(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE
    )

    //권한 체크
    fun checkSelfPermission(activity: Activity?, permission: String?): Boolean {
        val nResult = ActivityCompat.checkSelfPermission(activity!!, permission!!)
        return nResult == PackageManager.PERMISSION_GRANTED
    }

    //권한 요청
    fun requestPermissions(activity: Activity?) {
        ActivityCompat.requestPermissions(activity!!, PERMISSIONS, RESULT_CODE)
    }

    //권한 요청에 대한 승인 확인
    fun checkgranted(nResults: IntArray): Boolean {

        if (nResults.isEmpty()) {
            return false
        }

        for (result in nResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }

        return true
    }
}