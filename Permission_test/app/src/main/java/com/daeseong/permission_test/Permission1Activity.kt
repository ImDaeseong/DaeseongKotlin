package com.daeseong.permission_test

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.im.daeseong.permission_test.Util.PermissionUtil

class Permission1Activity : AppCompatActivity() {

    private val tag: String = Permission1Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission1)

        if (PermissionUtil.checkSelfPermission(this, Manifest.permission.CAMERA) &&
            PermissionUtil.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
            PermissionUtil.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
            PermissionUtil.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) ) {

            // 권한이 있으면 메인으로
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@Permission1Activity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)

        } else {

            // 권한이 없으면 권한 요청
            PermissionUtil.requestPermissions(this)
        }
    }

    //권한 요청시 응답
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {

        if (requestCode == PermissionUtil.RESULT_CODE) {

            if (PermissionUtil.checkGranted(grantResults)) {

                // 권한이 있으면 메인으로
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@Permission1Activity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1000)

            } else {

                // 권한이 없으면 종료
                finish()
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}
