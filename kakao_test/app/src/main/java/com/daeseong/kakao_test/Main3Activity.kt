package com.daeseong.kakao_test


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.AuthService
import com.kakao.auth.Session
import com.kakao.auth.network.response.AccessTokenInfoResponse
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.LoginButton
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.callback.UnLinkResponseCallback
import com.kakao.usermgmt.response.MeV2Response


class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private var loginButton: LoginButton? = null

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //카카오 로그인
        loginButton = findViewById(R.id.kakao_loginbtn)
        loginButton!!.setOnClickListener {

            //실행 시 로그인 토큰이 있으면 자동으로 로그인 수행
            Session.getCurrentSession().checkAndImplicitOpen()
        }

        //getNickname
        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {

            if (!Session.getCurrentSession().checkAndImplicitOpen()) {
                Log.e(tag, "연결해제된 아이디")
            }

            val keys: MutableList<String> = ArrayList()
            keys.add("properties.nickname")

            UserManagement.getInstance().me(keys, object : MeV2ResponseCallback() {
                override fun onSessionClosed(errorResult: ErrorResult) {}
                override fun onSuccess(result: MeV2Response) {

                    Log.e(tag, "getNickname : " + result.nickname)
                }
            })
        }

        //getUserId
        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {

            AuthService.getInstance().requestAccessTokenInfo(object :
                ApiResponseCallback<AccessTokenInfoResponse?>() {
                override fun onSessionClosed(errorResult: ErrorResult) {}
                override fun onNotSignedUp() {}
                override fun onSuccess(result: AccessTokenInfoResponse?) {
                    Log.e(tag, "getUserId : " + result!!.userId)
                }
            })
        }

        //로그아웃
        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener {

            UserManagement.getInstance().requestUnlink(object : UnLinkResponseCallback() {
                override fun onSessionClosed(errorResult: ErrorResult) {}
                override fun onNotSignedUp() {}
                override fun onSuccess(result: Long) {
                    Log.e(tag, "정상적으로 로그아웃되었습니다.")
                }
            })
        }
    }

}
