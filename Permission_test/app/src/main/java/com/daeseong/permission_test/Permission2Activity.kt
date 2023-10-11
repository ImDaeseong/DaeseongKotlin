package com.daeseong.permission_test

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.im.daeseong.permission_test.Util.PermissionUtilA

class Permission2Activity : AppCompatActivity() {

    private val tag: String = Permission2Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission2)
    }

    override fun onResume() {
        super.onResume()

        if (checkSelfPermission()) {

            // 권한이 있으면 메인으로
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@Permission2Activity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)

        } else {

            // 권한이 없으면 권한 요청
            ActivityCompat.requestPermissions(this, PermissionUtilA.PERMISSIONS, PermissionUtilA.RESULT_CODE)
        }
    }

    //정의된 모든 권한 체크
    private fun checkSelfPermission(): Boolean {

        for (permission in PermissionUtilA.PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Log.e(tag, "requestCode:$requestCode")

        for (result in grantResults) {

            // 거부된 권한이 있는 경우 다시 요청
            if (result == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, PermissionUtilA.PERMISSIONS, PermissionUtilA.RESULT_CODE)
                return
            }
        }
    }
}
