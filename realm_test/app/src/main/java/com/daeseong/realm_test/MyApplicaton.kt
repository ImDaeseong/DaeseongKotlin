package com.daeseong.realm_test

import android.app.Application
import android.content.res.Configuration
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplicaton : Application() {

    companion object {

        private lateinit var mInstance: MyApplicaton

        fun getInstance(): MyApplicaton {
            return mInstance
        }

        private var realm: Realm? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initRealm()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true) // UI Thread realm 접근 가능
            .deleteRealmIfMigrationNeeded() // db 변경사항 있을시 저장 데이터 모두 삭제
            .name("Lotto")
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        realm = Realm.getInstance(realmConfiguration)
    }

    fun getRealm(): Realm {
        return realm!!
    }
}
