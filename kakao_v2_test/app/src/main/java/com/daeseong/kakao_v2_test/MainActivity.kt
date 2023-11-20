package com.daeseong.kakao_v2_test

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
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

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button

    private val callback = KakaoCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getHashKey()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (!ShareClient.instance.isKakaoTalkSharingAvailable(this)) {
                Kakaoinstall(this)
                return@setOnClickListener
            }

            kakaoLogin()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {

            if (!ShareClient.instance.isKakaoTalkSharingAvailable(this)) {
                Kakaoinstall(this)
                return@setOnClickListener
            }

            kakaoLogout()
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {

            if (!ShareClient.instance.isKakaoTalkSharingAvailable(this)) {
                Kakaoinstall(this)
                return@setOnClickListener
            }

            kakaoTokenInfo()
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {

            if (!ShareClient.instance.isKakaoTalkSharingAvailable(this)) {
                Kakaoinstall(this)
                return@setOnClickListener
            }

            kakaoUnregister()
        }

        button5 = findViewById(R.id.button5)
        button5.setOnClickListener {

            if (!ShareClient.instance.isKakaoTalkSharingAvailable(this)) {
                Kakaoinstall(this)
                return@setOnClickListener
            }

            kakaoLink()
            //kakaolink_temp()
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
                Log.e(tag, "hashkey: " + Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT))
            } catch (ex: NoSuchAlgorithmException) {
                Log.e(tag, ex.message.toString())
            }
        }
    }

    //카카오 로그인
    private fun kakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            Log.e(tag, "Kakao 설치")
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            Log.e(tag, "Kakao 미설치")
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun loginInfo() {
        UserApiClient.instance.me { user: User?, throwable: Throwable? ->
            if (user != null) {
                try {
                    Log.e(tag, "getId: ${user.id}")
                    Log.e(tag, "getNickname: ${user?.kakaoAccount?.profile?.nickname}")
                    Log.e(tag, "getEmail: ${user.kakaoAccount?.email}")
                    Log.e(tag, "getGender: ${user.kakaoAccount?.gender}")
                    Log.e(tag, "getAgeRange: ${user.kakaoAccount?.ageRange}")
                    Log.e(tag, "getProfileImageUrl: ${user?.kakaoAccount?.profile?.profileImageUrl}")
                    Log.e(tag, "카카오 로그인 성공")
                } catch (ex: Exception) {
                    Log.e(tag, ex.message.toString())
                }
            } else {
                Log.e(tag, "카카오 로그인 실패: $throwable")
            }
            null
        }
    }

    //카카오 로그아웃
    private fun kakaoLogout() {
        UserApiClient.instance.logout { throwable: Throwable? ->
            if (throwable != null) {
                Log.e(tag, "Kakao 로그아웃 실패")
            } else {
                Log.e(tag, "Kakao 로그아웃 성공")
            }
            null
        }
    }

    //카카오 토큰 정보
    private fun kakaoTokenInfo() {
        UserApiClient.instance.accessTokenInfo { accessTokenInfo: AccessTokenInfo?, throwable: Throwable? ->
            if (throwable != null) {
                Log.e(tag, "Kakao 토큰 정보 호출 실패")
            } else if (accessTokenInfo != null) {
                Log.e(tag, "getId: ${accessTokenInfo.id}")
                Log.e(tag, "만료시간: ${accessTokenInfo.expiresIn}")
            }
            null
        }
    }

    //카카오 탈퇴
    private fun kakaoUnregister() {
        UserApiClient.instance.unlink { throwable: Throwable? ->
            if (throwable != null) {
                Log.e(tag, "Kakao 토큰 회원탈퇴 실패")
            } else {
                Log.e(tag, "Kakao 토큰 회원탈퇴 성공")
            }
            null
        }
    }

    private fun kakaoLink() {

        //v1 에서  v2 변경된 내용
        //KakaoLinkService  -> ShareClient
        //KakaoTalkService  -> TalkApiClient

        if (!ShareClient.instance.isKakaoTalkSharingAvailable(this))
            return

        val imgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
        val title = "[나의앱]\n나의앱 제목!"
        val desc = "나의앱에 대한 설명과 링크 정보:\nhttps://m.naver.com"
        val linkUrl = ""

        val content = Content(title, imgUrl, Link(linkUrl, linkUrl), desc)
        val itemContent = ItemContent()
        val social = Social()

        val button = com.kakao.sdk.template.model.Button("앱에서 보기", Link(linkUrl, linkUrl))
        val buttons = arrayOf(button)
        val feedTemplate = FeedTemplate(content, itemContent, social, buttons.toList())

        ShareClient.instance.shareDefault(this, feedTemplate) { sharingResult, error ->
            if (error != null) {
                Log.e(tag, "KakaoTalk 공유 실패: $error")
            } else if (sharingResult != null) {
                startActivity(sharingResult.intent)
                Log.e(tag, "${sharingResult.warningMsg}")
                Log.e(tag, "${sharingResult.argumentMsg}")
                Log.e(tag, "${sharingResult.warningMsg.size}")
            }
            null
        }
    }

    private fun kakaolink_temp() {

        //v1 에서  v2 변경된 내용
        //KakaoLinkService  -> ShareClient
        //KakaoTalkService  -> TalkApiClient

        if (!ShareClient.instance.isKakaoTalkSharingAvailable(this))
            return

        val imgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
        val title = "[나의앱]\n나의앱 제목!"
        val desc = "나의앱에 대한 설명과 링크 정보:\nhttps://m.naver.com"
        val linkUrl = "https://developers.kakao.com"

        val content = Content(title, imgUrl, Link(linkUrl, linkUrl), desc)
        val itemContent = ItemContent()
        val social = Social()

        val Button1 = Button("앱에서 보기", Link(linkUrl, linkUrl))
        val Button2 = Button("앱에서 보기1", Link("", ""))
        val buttons = arrayOf(Button1, Button2)
        val feedTemplate = FeedTemplate(content, itemContent, social, buttons.toList())

        ShareClient.instance.shareDefault(this, feedTemplate) { sharingResult, error ->
            if (error != null) {
                Log.e(tag, "KakaoTalk 공유 실패: $error")
            } else if (sharingResult != null) {
                startActivity(sharingResult.intent)
                Log.e(tag, "${sharingResult.warningMsg}")
                Log.e(tag, "${sharingResult.argumentMsg}")
                Log.e(tag, "${sharingResult.warningMsg.size}")
            }
            null
        }
    }

    private inner class KakaoCallback : (OAuthToken?, Throwable?) -> Unit {
        override fun invoke(token: OAuthToken?, error: Throwable?) {
            token?.let {
                if (it.idToken != null) {
                    Log.e(tag, "getIdToken: ${it.idToken}")
                } else if (it.accessToken != null) {
                    Log.e(tag, "getAccessToken: ${it.accessToken}")
                }
                loginInfo()
            }
        }
    }

    private fun Kakaoinstall(activity: Activity) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=" + "com.kakao.talk")
        activity.startActivity(intent)
    }
}