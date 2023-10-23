package com.daeseong.kakao_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var sessionCallback: SessionCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main1)

        initkakao_login()
    }

    override fun onDestroy() {
        super.onDestroy()

        Session.getCurrentSession().removeCallback(sessionCallback)
    }

    private fun initkakao_login() {

        sessionCallback = SessionCallback()
        Session.getCurrentSession().addCallback(sessionCallback)

        //실행 시 로그인 토큰이 있으면 자동으로 로그인 수행
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {

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

    private inner class SessionCallback : ISessionCallback {

        //로그인 성공한 상태
        override fun onSessionOpened() {

            Log.e(tag, "onSessionOpened")

            if (Session.getCurrentSession().isOpened) {
                SuccessCallActivity()
            }
        }

        //로그인 실패한 상태
        override fun onSessionOpenFailed(ex: KakaoException) {

            Log.e(tag, "onSessionOpenFailed:" + ex.message.toString())
        }
    }
}