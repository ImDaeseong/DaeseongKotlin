package com.daeseong.kakao_v2_test

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient

class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                KakaoTalkLogin()
            } else {
                KakaoAccountLogin()
            }
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            kakaoLogout()
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            kakaoTokenInfo()
        }
    }

    private fun KakaoTalkLogin() {

        UserApiClient.instance.loginWithKakaoTalk(this) { oAuthToken, error ->
            if (error != null) {
                Log.e(tag, "로그인 실패", error)
            } else if (oAuthToken != null) {
                Log.e(tag, "로그인 성공(토큰) : ${oAuthToken.accessToken}")
                loginInfo()
            }
            null
        }
    }

    private fun KakaoAccountLogin() {

        UserApiClient.instance.loginWithKakaoAccount(this) { oAuthToken, error ->
            if (error != null) {
                Log.e(tag, "로그인 실패", error)
            } else if (oAuthToken != null) {
                Log.e(tag, "로그인 성공(토큰) : ${oAuthToken.accessToken}")
                loginInfo()
            }
            null
        }
    }

    private fun loginInfo() {
        UserApiClient.instance.me { user, throwable ->
            if (user != null) {
                try {
                    Log.e(tag, "getId : ${user.id}")
                    Log.e(tag, "카카오 로그인 성공")
                    //Log.e(tag, "getNickname : ${user.kakaoAccount?.profile?.nickname}")
                    //Log.e(tag, "getEmail : ${user.kakaoAccount?.email}")
                    //Log.e(tag, "getGender : ${user.kakaoAccount?.gender}")
                    //Log.e(tag, "getAgeRange : ${user.kakaoAccount?.ageRange}")
                    //Log.e(tag, "getProfileImageUrl : ${user.kakaoAccount?.profile?.profileImageUrl}")

                } catch (ex: Exception) {
                    Log.e(tag, ex.message.toString())
                }
            } else {
                Log.e(tag, "카카오 로그인 실패: ", throwable)
            }
            null
        }
    }

    // 카카오 로그아웃
    private fun kakaoLogout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(tag, "kakao 로그아웃 실패")
            } else {
                Log.e(tag, "kakao 로그아웃 성공")
            }
            null
        }
    }

    // 카카오 토큰 정보
    private fun kakaoTokenInfo() {
        UserApiClient.instance.accessTokenInfo { accessTokenInfo, throwable ->
            if (throwable != null) {
                Log.e(tag, "kakao 토큰 정보 호출 실패")
            } else if (accessTokenInfo != null) {
                Log.e(tag, "getId:${accessTokenInfo.id}")
                Log.e(tag, "만료시간:${accessTokenInfo.expiresIn}")
            }
            null
        }
    }

}