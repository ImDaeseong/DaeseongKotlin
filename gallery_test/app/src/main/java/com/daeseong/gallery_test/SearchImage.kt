package com.daeseong.gallery_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.File

object SearchImage {

    private val ROOT_DIR: String = Environment.getExternalStorageDirectory().path
    private val PICTURES = "$ROOT_DIR/Pictures"
    private val CAMERA = "$ROOT_DIR/DCIM/Camera"

    fun getAllPicture(): ArrayList<String> {

        val allPicture: ArrayList<String> = ArrayList()
        val allDir: ArrayList<String> = ArrayList()

        getDirList(PICTURES, allDir)
        allDir.add(CAMERA)

        for (dir in allDir) {
            getImageList(dir, allPicture)
        }

        return allPicture
    }

    private fun getDirList(sPath: String, allDir: ArrayList<String>) {
        val dir = File(sPath)
        dir.listFiles()?.forEach { current ->
            if (current.isDirectory) {
                allDir.add(current.absolutePath)
            }
        }
    }

    private fun getImageList(sPath: String, allPicture: ArrayList<String>) {
        val dir = File(sPath)
        dir.listFiles()?.forEach { current ->
            if (current.isFile) {
                val sImagePath: String = current.absolutePath
                val sSearch = getExtName(sImagePath)
                if (sSearch == "jpeg" || sSearch == "jpg" || sSearch == "png") {
                    allPicture.add(sImagePath)
                }
            }
        }
    }

    private fun getExtName(sPath: String): String {
        return try {
            var path = sPath
            if (path.contains("/")) {
                path = path.substring(path.lastIndexOf("/") + 1)
            }
            if (!path.contains(".")) {
                ""
            } else path.substring(path.lastIndexOf(".") + 1)
        } catch (e: Exception) {
            ""
        }
    }

    fun getFileName(sPath: String): String {
        return try {
            if (!sPath.contains("/")) {
                sPath
            } else sPath.substring(sPath.lastIndexOf("/") + 1)
        } catch (e: Exception) {
            sPath
        }
    }

    fun loadBitmap(sPath: String): Bitmap {
        val option = BitmapFactory.Options()
        option.inSampleSize = 10
        return BitmapFactory.decodeFile(sPath, option)
    }
}
