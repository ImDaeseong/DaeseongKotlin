package com.daeseong.gameinfo_test

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.util.Log

class GameInfo {

    private val tag: String = GameInfo::class.java.simpleName

    private val GAME_PACKAGE_NAMES = arrayOf(
        "com.nexon.overhit",  // 오버히트
        "com.netmarble.tera"   // 테라M
    )

    fun getGameApp(context: Context): List<GameItem> {
        val gameList: MutableList<GameItem> = ArrayList()
        val packages = context.packageManager.getInstalledPackages(0)

        for (info in packages) {
            val appName = info.applicationInfo.loadLabel(context.packageManager).toString()
            val packageName = info.packageName
            val versionName = info.versionName
            val versionCode = info.versionCode
            val gameIcon = info.applicationInfo.loadIcon(context.packageManager)

            if (info.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {

                Log.e(tag, "Installed package (User) :$packageName")
                getApkPath(context, packageName)

                if (isGamePackageName(packageName)) {
                    val item = GameItem(appName, packageName, versionName, versionCode, gameIcon)
                    gameList.add(item)
                }
            }
        }

        return gameList
    }

    fun getApkPath(context: Context, packageName: String): String {
        var sSourceDir: String? = null
        try {
            sSourceDir = context.packageManager.getApplicationInfo(packageName, 0).sourceDir
        } catch (e: Exception) {
            // Handle exception if needed
        }
        return sSourceDir.orEmpty()
    }

    private fun isGamePackageName(packageName: String): Boolean {
        return GAME_PACKAGE_NAMES.any { packageName.startsWith(it) }
    }

    data class GameItem(
        var appName: String = "",
        var packageName: String = "",
        var versionName: String = "",
        var versionCode: Int = 0,
        var gameIcon: Drawable? = null
    )
}
