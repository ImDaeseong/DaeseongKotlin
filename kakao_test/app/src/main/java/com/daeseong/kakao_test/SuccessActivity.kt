package com.daeseong.kakao_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.ButtonObject
import com.kakao.message.template.ContentObject
import com.kakao.message.template.FeedTemplate
import com.kakao.message.template.LinkObject
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.util.exception.KakaoException

class SuccessActivity : AppCompatActivity() {

    private val tag = SuccessActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null

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

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {

            kakaolink()
        }
    }

    private fun kakaolink() {

        val imgUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
        val title = "[나의앱]\n나의앱 제목!"
        val desc = "나의앱에 대한 설명과 링크 정보:\nhttps://m.naver.com"

        try {
            val params = FeedTemplate
                .newBuilder(
                    ContentObject.newBuilder(
                        title, imgUrl, LinkObject.newBuilder()
                            .setWebUrl("").setMobileWebUrl("").build()
                    )
                        .setDescrption(desc)
                        .build()
                )
                .addButton(
                    ButtonObject(
                        "앱에서 보기", LinkObject.newBuilder()
                            .setAndroidExecutionParams("key1=value1")
                            .setIosExecutionParams("key1=value1").build()
                    )
                )
                .build()
            KakaoLinkService.getInstance().sendDefault(
                this@SuccessActivity,
                params,
                object : ResponseCallback<KakaoLinkResponse>() {
                    override fun onFailure(errorResult: ErrorResult) {
                        Log.e(tag, errorResult.toString())
                    }

                    override fun onSuccess(result: KakaoLinkResponse) {
                        Log.e(tag, result.toString())
                    }
                })
        } catch (ex: KakaoException) {
        } catch (ex: Exception) {
        }

    }

}
