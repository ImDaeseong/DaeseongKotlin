package com.daeseong.kakao_v2_test

import android.app.Application
import com.kakao.sdk.common.KakaoSdk.init

class GlobalApplication : Application() {

    companion object {
        private lateinit var mInstance: GlobalApplication

        fun getInstance(): GlobalApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this

        // Kakao Sdk 초기화
        init(this, resources.getString(R.string.kakao_id))
    }

    override fun onTerminate() {
        super.onTerminate()

    }
}