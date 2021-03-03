package com.daeseong.permission_test

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.permission_test.Util.PermissionUtil
import com.daeseong.permission_test.Util.PermissionUtil.checkSelfPermission
import com.daeseong.permission_test.Util.PermissionUtil.checkgranted
import com.daeseong.permission_test.Util.PermissionUtil.requestPermissions


class Permission1Activity : AppCompatActivity() {

    private val tag: String = Permission1Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission1)

        if (checkSelfPermission(this, Manifest.permission.CAMERA) &&
            checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
            checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
            checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) ) {

            Log.e(tag, "권한이 있으면 메인으로")

            Handler().postDelayed(Runnable {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)

        } else {

            Log.e(tag, "권한이 없으면 권한 요청")

            requestPermissions(this)
        }
    }

    //권한 요청시 응답
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {

        Log.e(tag, "requestCode:$requestCode")

        if (requestCode == PermissionUtil.RESULT_CODE) {

            if (checkgranted(grantResults)) {

                //권한이 있으면 메인으로
                Handler().postDelayed({

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1000)

            } else {

                //권한이 없으면 종료
                finish()
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}
