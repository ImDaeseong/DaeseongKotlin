package com.daeseong.fcm_test

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private val channelId by lazy { getString(R.string.default_notification_channel_id) }


    private lateinit var permissResultLauncher: ActivityResultLauncher<String>

    private fun initPermission() {
        permissResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                Log.e(tag, "POST_NOTIFICATIONS 권한 소유")
            } else {
                Log.e(tag, "POST_NOTIFICATIONS 권한 미소유")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        initPermission()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                Log.e(tag, "POST_NOTIFICATIONS 권한 소유")
            }

        } else {
            Log.e(tag, "sdk 33 이하")
        }
    }

    private fun init() {

        val channel = NotificationChannel(channelId,"fcm_testPush", NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "fcm_test Push"
        }

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        try {

            // Firebase 초기화
            //Firebase.initialize(this)

            //FCM 푸시 구독 방식
            FirebaseMessaging.getInstance().subscribeToTopic("all")
            FirebaseMessaging.getInstance().subscribeToTopic("notice")
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    Log.e(tag, "Token = $token")
                }
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

}