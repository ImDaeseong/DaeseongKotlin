package com.daeseong.serviceboot_completed_test

import android.content.Context
import android.os.Environment
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class LogToFile_util {

    private var tag: String = LogToFile_util::class.java.simpleName

    companion object {
        private var instance: LogToFile_util? = null
        fun getInstance(): LogToFile_util {
            if (instance == null) {
                instance = LogToFile_util()
            }
            return instance as LogToFile_util
        }
    }

    private var mContext: Context? = null

    private var logFile: File? = null

    private val logSDF = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    private val LOG_MAX_SIZE = 2 * 1024 * 1024

    fun init(context: Context) {

        mContext = context

        logFile = getLogFile()

        val logFileSize: Long = getFileSize(logFile!!)
        if (LOG_MAX_SIZE < logFileSize) {
            resetLogFile()
        }
    }

    fun write(strLog: String) {

        val logStr: String = getFunctionInfo().toString() + " - " + strLog
        try {
            val bw = BufferedWriter(FileWriter(logFile, true))
            bw.write(logStr)
            bw.write("\r\n")
            bw.flush()
        } catch (e: Exception) {
        }
    }

    private fun getLogFile(): File? {

        val file: File = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(mContext!!.getExternalFilesDir("BootserviceLog")!!.path + "/")
        } else {
            File(mContext!!.filesDir.path + "/BootserviceLog/")
        }

        if (!file.exists()) {
            file.mkdir()
        }

        val logFile = File(file.path + "/logs.txt")
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: java.lang.Exception) {
            }
        }
        return logFile
    }

    private fun getFunctionInfo(): String? {

        val sts = Thread.currentThread().stackTrace ?: return null

        for (st in sts) {

            if (st.isNativeMethod) {
                continue
            }

            if (st.className == Thread::class.java.name) {
                continue
            }

            tag = st.fileName
            return "[" + logSDF.format(Date()) + " " + st.className + " " + st.methodName + " Line:" + st.lineNumber + "]"
        }
        return null
    }

    private fun resetLogFile() {

        val lastLogFile = File(logFile!!.parent + "/lastLog.txt")
        if (lastLogFile.exists()) {
            lastLogFile.delete()
        }
        logFile!!.renameTo(lastLogFile)
        try {
            logFile!!.createNewFile()
        } catch (e: java.lang.Exception) {
        }
    }

    private fun getFileSize(file: File): Long {

        var size: Long = 0
        if (file.exists()) {
            try {
                val fis = FileInputStream(file)
                size = fis.available().toLong()
            } catch (e: java.lang.Exception) {
            }
        }
        return size
    }
}