package com.daeseong.gameinfo_test

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.util.Log


class GameInfo {

    private val tag: String = GameInfo::class.java.simpleName

    /*
    private val GAME_PACKAGE_NAMES = arrayOf(
        "ds.id.Omong",
        "ds.id.Bahasa",
        "ds.id.BahasaKorea"
    )
    */

    private val GAME_PACKAGE_NAMES = arrayOf(
        "com.nexon.overhit",  //오버히트
        "com.netmarble.tera" //테라M
    )

    fun getGameApp(context: Context): List<GameItem> {

        val gameList: MutableList<GameItem> = ArrayList()
        val packages = context.packageManager.getInstalledPackages(0)

        for (i in packages.indices) {

            val info = packages[i]
            val appName = info.applicationInfo.loadLabel(context.packageManager).toString()
            val packageName = info.packageName
            val versionName = info.versionName
            val versoinCode = info.versionCode
            val gameIcon = info.applicationInfo.loadIcon(context.packageManager)

            if (info.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {

                //Log.d(tag, "Installed package (User) :$packageName")
                //getApkPath(context, packageName)

                if (isGamePackageName(packageName)) {

                    val item = GameItem()
                    item.appName = appName
                    item.packageName = packageName
                    item.versionName = versionName
                    item.versoinCode = versoinCode
                    item.gameIcon = gameIcon
                    gameList.add(item)
                }

            } else {

                //Log.e(tag, "Installed package (System) :$packageName")
            }
        }

        return gameList
    }

    fun getApkPath(context: Context, packageName: String): String {

        var sSourceDir: String? = null
        try {

            sSourceDir = context.packageManager.getApplicationInfo(packageName, 0).sourceDir
            //Log.e(tag, "sSourceDir2:$sSourceDir");

        } catch (e: Exception) {
        }
        return sSourceDir!!
    }

    private fun isGamePackageName(packageName: String): Boolean {

        var size: Int = GAME_PACKAGE_NAMES.size

        for (i in 0 until size) {

            if (packageName.startsWith(GAME_PACKAGE_NAMES[i])) {

                //Log.e(tag, "isGamePackageName:" + GAME_PACKAGE_NAMES[i].toString());

                return true
            }
        }
        return false
    }


    class GameItem {
        var appName = ""
        var packageName = ""
        var versionName = ""
        var versoinCode = 0
        var gameIcon: Drawable? = null
    }
}
