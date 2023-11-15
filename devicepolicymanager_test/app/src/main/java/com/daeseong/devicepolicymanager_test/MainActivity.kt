package com.daeseong.devicepolicymanager_test

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button

    private lateinit var vname: ComponentName
    private lateinit var devicePolicyManager: DevicePolicyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            enableAdmin()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            disableAdmin()
        }

        // 관리자 권한 추가
        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        vname = ComponentName(this, DeviceAdminReceiverEx::class.java)
        Log.e(tag, ":$vname")
    }

    private fun enableAdmin() {
        val bAdmin = devicePolicyManager.isAdminActive(vname)
        if (bAdmin) {
            // 화면 잠금
            devicePolicyManager.lockNow()
        } else {
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, vname)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"안전한 환경을 위해 실행 버튼을 클릭해주세요.")
            startActivity(intent)
        }
    }

    private fun disableAdmin() {
        val bAdmin = devicePolicyManager.isAdminActive(vname)
        if (bAdmin) {
            devicePolicyManager.removeActiveAdmin(vname)
        }
    }
}
