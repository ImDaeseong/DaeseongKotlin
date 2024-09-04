package com.daeseong.realm_test

import android.app.Application
import android.content.res.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApplication : Application() {

    companion object {
        private lateinit var mInstance: MyApplication

        fun getInstance(): MyApplication {
            return mInstance
        }

        private lateinit var realmConfiguration: RealmConfiguration
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initRealm()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    // Realm 초기화 함수
    private fun initRealm() {
        realmConfiguration = RealmConfiguration.Builder(schema = setOf(Lotto::class))
            .name("Lotto") // 데이터베이스 이름
            .deleteRealmIfMigrationNeeded() // db 변경사항 있을시 저장 데이터 모두 삭제
            .build()
    }

    fun getRealm(): Realm {
        // Realm Kotlin에서는 매번 새로운 Realm 인스턴스를 생성하는 것이 권장
        return Realm.open(realmConfiguration)
    }
}