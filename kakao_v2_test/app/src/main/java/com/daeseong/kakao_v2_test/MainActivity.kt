package com.daeseong.kakao_v2_test

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.template.model.*
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.AccessTokenInfo
import com.kakao.sdk.user.model.User
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null
    private var button5 : Button? = null

    private val callback = kakaoCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getHashKey()

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            kakaologin()
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            kakaologout()
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

            kakaoTokenInfo()
        }

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener {

            kakaounReg()
        }

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener {

            kakaolink()
        }
    }

    private fun getHashKey() {

        var packageInfo: PackageInfo? = null

        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (ex: PackageManager.NameNotFoundException) {
            Log.e(tag, ex.message.toString())
        }

        for (signature in packageInfo!!.signatures) {

            try {
                val messageDigest: MessageDigest = MessageDigest.getInstance("SHA")
                messageDigest.update(signature.toByteArray())
                Log.e(tag, "haskey:" + Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT))
            } catch (ex: NoSuchAlgorithmException) {
                Log.e(tag, ex.message.toString())
            }
        }
    }

    //카카오 로그인
    private fun kakaologin() {

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            Log.e(tag, "kakao 설치")
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            Log.e(tag, "kakao 미설치")
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun LoginInfo() {
        UserApiClient.instance.me { user: User?, throwable: Throwable? ->
            if (user != null) {
                try {
                    Log.e(tag, "getId : " + user.id)
                    Log.e(tag, "getNickname : " + user?.kakaoAccount?.profile?.nickname);
                    Log.e(tag, "getEmail : " + user.kakaoAccount?.email)
                    Log.e(tag, "getGender : " + user.kakaoAccount?.gender)
                    Log.e(tag, "getAgeRange : " + user.kakaoAccount?.ageRange)
                    Log.e(tag, "getProfileImageUrl : " + user?.kakaoAccount?.profile?.profileImageUrl)
                    Log.e(tag, "카카오 로그인 성공")
                } catch (ex: Exception) {
                    Log.e(tag, ex.message.toString())
                }
            } else {
                Log.e(tag, "카카오 로그인 실패: ", throwable)
            }
            null
        }
    }

    //카카오 로그아웃
    private fun kakaologout() {
        UserApiClient.instance.logout { throwable: Throwable? ->
            if (throwable != null) {
                Log.e(tag, "kakao 로그아웃 실패")
            } else {
                Log.e(tag, "kakao 로그아웃 성공")
            }
            null
        }
    }

    //카카오 토큰 정보
    private fun kakaoTokenInfo() {
        UserApiClient.instance
            .accessTokenInfo { accessTokenInfo: AccessTokenInfo?, throwable: Throwable? ->
                if (throwable != null) {
                    Log.e(tag, "kakao 토큰 정보 호출 실패")
                } else if (accessTokenInfo != null) {
                    Log.e(tag, "getId:" + accessTokenInfo.id)
                    Log.e(tag, "만료시간:" + accessTokenInfo.expiresIn)
                }
                null
            }
    }

    //카카오 탈퇴
    private fun kakaounReg() {
        UserApiClient.instance.unlink { throwable: Throwable? ->
            if (throwable != null) {
                Log.e(tag, "kakao 토큰 회원탈퇴 실패")
            } else {
                Log.e(tag, "kakao 토큰 회원탈퇴 성공")
            }
            null
        }
    }

    private fun kakaolink() {

        if (!ShareClient.instance.isKakaoTalkSharingAvailable(this))
            return

        val title = "제목"
        val imgUrl = ""
        val desc = ""

        val content = Content(title, imgUrl, Link(imgUrl, imgUrl), desc)
        val itemContent = ItemContent()
        val social = Social()

        val button = com.kakao.sdk.template.model.Button("자세히 보기", Link(imgUrl, imgUrl))
        val buttons = arrayOf(button)

        val feedTemplate = FeedTemplate(content, itemContent, social, buttons.toList(), "")

        ShareClient.instance.shareDefault(this, feedTemplate) { sharingResult, error ->
            if (error != null) {
                Log.e(tag, "카카오톡 친구 목록 가져오기 실패: $error")
            } else {
                Log.e(tag, sharingResult!!.warningMsg.size.toString())
            }
            null
        }

        /*
        if (!ShareClient.instance.isKakaoTalkSharingAvailable(this)) {
            return
        }

        TalkApiClient.instance.friends { friendFriends, error ->
            if (error != null) {
                Log.e(tag, "카카오톡 친구 목록 가져오기 실패:$error")
            } else {
                if (friendFriends!!.elements!!.isEmpty()) {
                    Log.e(tag, "메시지를 보낼 수 있는 친구가 없습니다.")
                } else {
                    Log.e(tag, friendFriends.toString())
                }
            }
            null
        }
        */
    }

    private inner class kakaoCallback : (OAuthToken?, Throwable?) -> Unit {
        override fun invoke(token: OAuthToken?, error: Throwable?) {
            token?.let {
                if (it.idToken != null) {
                    Log.e(tag, "getIdToken:" + it.idToken)
                } else if (it.accessToken != null) {
                    Log.e(tag, "getAccessToken:" + it.accessToken)
                }
                LoginInfo()
            }
        }
    }

}