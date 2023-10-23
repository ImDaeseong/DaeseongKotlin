package com.daeseong.kakao_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.LoginButton
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var sessionCallback: SessionCallback? = null

    private var loginButton: LoginButton? = null
    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)
        initkakao()

        //로그인버튼 숨기기
        loginButton = findViewById(R.id.kakao_loginbtn)
        loginButton!!.visibility = View.GONE
        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            //숨긴 로그인 버튼 클릭
            loginButton!!.performClick()
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        Session.getCurrentSession().removeCallback(sessionCallback)
    }

    private fun initkakao() {

        sessionCallback = SessionCallback()
        Session.getCurrentSession().addCallback(sessionCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun SuccessCallActivity() {
        val intent = Intent(this, SuccessActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun FailedCallActivity() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private inner class SessionCallback : ISessionCallback {

        //로그인 성공한 상태
        override fun onSessionOpened() {
            Log.e(tag, "onSessionOpened")

            if (Session.getCurrentSession().isOpened) {
                requestMe()
            }
        }

        //로그인 실패한 상태
        override fun onSessionOpenFailed(ex: KakaoException) {
            Log.e(tag, "onSessionOpenFailed:" + ex.message.toString())
        }

        private fun requestMe() {

            val keys: MutableList<String> = ArrayList()
            keys.add("properties.nickname")
            keys.add("properties.profile_image")
            keys.add("kakao_account.email")
            UserManagement.getInstance().me(keys, object : MeV2ResponseCallback() {

                override fun onFailure(errorResult: ErrorResult) {
                    Log.e(tag, "onSessionOpenFailed:" + errorResult.exception.toString())

                    //실패시 이동할 페이지
                    FailedCallActivity()
                }

                override fun onSessionClosed(errorResult: ErrorResult) {}
                override fun onSuccess(response: MeV2Response) {

                    try {

                        Log.e(tag, "user id : " + response.id)
                        Log.e(tag, "nickname : " + response.nickname)

                        if (response.kakaoAccount != null) {
                            Log.e(tag, "email: " + response.kakaoAccount.email)
                        }

                    } catch (ex: Exception) {
                        Log.e(tag, ex.message.toString())
                    }

                    //성공시 이동할 페이지
                    SuccessCallActivity()
                }
            })
        }
    }

}
