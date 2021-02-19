package com.daeseong.gallery_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.annotation.Nullable
import java.io.File


object SearchImage {

    var ROOT_DIR: String = Environment.getExternalStorageDirectory().path
    var PICTURES = "$ROOT_DIR/Pictures"
    var CAMERA = "$ROOT_DIR/DCIM/Camera"
    private var allDir: ArrayList<String>? = null
    private var allPicture: ArrayList<String>? = null

    fun getAllPicture(): ArrayList<String>? {

        allPicture = ArrayList()
        allDir = ArrayList()

        if (getDirList(PICTURES) != null) {

            allDir = getDirList(PICTURES)
        }

        allDir!!.add(CAMERA)
        for (dir in allDir!!) {

            getImageList(dir)
        }

        return allPicture
    }

    private fun getDirList(sPath: String): ArrayList<String>? {

        val dir = File(sPath)
        val fileList: Array<File> = dir.listFiles() ?: return null
        for (current in fileList) {
            if (current.isDirectory) {
                allDir!!.add(current.absolutePath)
            }
        }
        return allDir
    }

    @Nullable
    private fun getImageList(sPath: String) {

        val dir = File(sPath)
        val fileList: Array<File> = dir.listFiles()
        if (fileList != null) {
            for (current in fileList) {
                if (current.isFile) {
                    val sImagePath: String = current.absolutePath
                    val sSearch = getExtName(sImagePath)
                    if (sSearch == "jpeg" || sSearch == "jpg" || sSearch == "png") {
                        allPicture!!.add(sImagePath)
                    }
                }
            }
        }
    }

    private fun getExtName(sPath: String): String {
        var sPath = sPath
        try {
            if (sPath.contains("/")) {
                sPath = sPath.substring(sPath.lastIndexOf("/") + 1)
            }
            return if (!sPath.contains(".")) {
                ""
            } else sPath.substring(sPath.lastIndexOf(".") + 1)
        } catch (e: Exception) {
        }
        return ""
    }

    fun getFileName(sPath: String): String {
        try {
            return if (!sPath.contains("/")) {
                sPath
            } else sPath.substring(sPath.lastIndexOf("/") + 1)
        } catch (e: Exception) {
        }
        return sPath
    }

    fun loadBitmap(sPath: String?): Bitmap {
        val option = BitmapFactory.Options()
        option.inSampleSize = 10
        return BitmapFactory.decodeFile(sPath, option)
    }
}