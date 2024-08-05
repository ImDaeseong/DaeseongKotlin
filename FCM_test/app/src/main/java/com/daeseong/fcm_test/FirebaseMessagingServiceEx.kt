package com.daeseong.fcm_test

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseMessagingServiceEx : FirebaseMessagingService() {

    private val tag = FirebaseMessagingService::class.java.simpleName

    lateinit var sTitle:String
    lateinit var SMsg:String

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        try {

            if (message.data.isNotEmpty()) {
                Log.e(tag, "getData: " + message.notification)
            }

            if (message.notification != null) {

                sTitle = message.notification?.title.toString()
                SMsg = message.notification?.body.toString()
                Log.e(tag, "getTitle:$SMsg")
                Log.e(tag, "getBody:$SMsg")

                //sendNotification(sTitle, SMsg)
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

}
