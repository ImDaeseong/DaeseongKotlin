package com.daeseong.kakao_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback


class SuccessActivity : AppCompatActivity() {

    private val tag = SuccessActivity::class.java.simpleName

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {

            UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
                override fun onCompleteLogout() {

                    Log.e(tag, "onCompleteLogout:" + "정상적으로 로그아웃되었습니다.")

                    val intent = Intent(this@SuccessActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }
}
