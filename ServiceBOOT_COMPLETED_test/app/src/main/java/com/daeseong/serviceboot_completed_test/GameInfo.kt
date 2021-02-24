package com.daeseong.serviceboot_completed_test

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.util.Log


object GameInfo {

    private val tag = GameInfo::class.java.simpleName

    fun getGameApp(context: Context): List<GameItem> {

        val gameList: MutableList<GameItem> = ArrayList()
        val packages: List<PackageInfo> = context.packageManager.getInstalledPackages(0)

        for (i in packages.indices) {
            val info = packages[i]
            val appName = info.applicationInfo.loadLabel(context.packageManager).toString()
            val packageName = info.packageName
            val versionName = info.versionName
            val versoinCode = info.versionCode
            val gameIcon = info.applicationInfo.loadIcon(context.packageManager)

            if (info.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {

                Log.d(tag, "Installed package (User) :$packageName")

                val item = GameItem(appName, packageName, versionName, versoinCode, gameIcon)
                gameList.add(item)

            } else {
                Log.e(tag, "Installed package (System) :$packageName")
            }
        }
        return gameList
    }

    fun runApp(context: Context, sPackage: String?): Boolean {

        var bRun = false
        try {
            val intent: Intent? = context.packageManager.getLaunchIntentForPackage(sPackage!!)
            if (intent != null) {
                context.startActivity(intent)
                bRun = true
            }
        } catch (e: Exception) {
        }
        return bRun
    }
}