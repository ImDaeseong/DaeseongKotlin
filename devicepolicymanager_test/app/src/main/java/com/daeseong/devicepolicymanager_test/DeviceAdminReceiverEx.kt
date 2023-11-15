package com.daeseong.devicepolicymanager_test

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DeviceAdminReceiverEx : DeviceAdminReceiver() {

    private val tag = DeviceAdminReceiverEx::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        Log.e(tag, "onReceive:${intent.action}")
    }

    override fun onLockTaskModeEntering(context: Context, intent: Intent, pkg: String) {
        super.onLockTaskModeEntering(context, intent, pkg)

        Log.e(tag, "onLockTaskModeEntering:$pkg")
    }

    override fun onEnabled(context: Context, intent: Intent) {
        Log.e(tag, "DeviceAdminReceiverEx DEVICE_ADMIN_ENABLED")
    }

    override fun onDisabled(context: Context, intent: Intent) {
        Log.e(tag, "DeviceAdminReceiverEx DEVICE_ADMIN_DISABLED")
    }
}
