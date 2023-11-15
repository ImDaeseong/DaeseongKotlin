package com.daeseong.audiorecorder_test

import android.content.Context
import android.media.MediaRecorder
import android.os.Environment
import java.io.File
import java.io.IOException

class AudioRecorder private constructor(private val context: Context) {

    companion object {

        private val tag = AudioRecorder::class.java.name

        private var instance: AudioRecorder? = null

        @JvmStatic
        fun getInstance(context: Context): AudioRecorder {
            if (instance == null) {
                instance = AudioRecorder(context)
            }
            return instance!!
        }
    }

    private var recorder: MediaRecorder? = null
    private var saveFolder: File? = null
    private var isRecording = false
    private var recordingStart: Long = 0

    fun startRecord(sfilename: String): Boolean {
        if (!isRecording) {
            val sFile = "$sfilename.aac"
            saveFolder = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
                sFile
            )

            recorder = MediaRecorder()

            try {
                recorder?.apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                    setOutputFile(saveFolder?.absolutePath)
                    prepare()
                    start()

                    recordingStart = System.currentTimeMillis()
                    isRecording = true
                }
                return true
            } catch (iex: IOException) {
                iex.printStackTrace()
                return false
            }
        } else {
            return true
        }
    }

    fun stopRecord(): Int {
        var nDuration = 0

        recorder?.let {
            nDuration = ((System.currentTimeMillis() - recordingStart) / 1000).toInt()
            isRecording = false

            if (isRecording) {
                it.stop()
            }

            it.reset()
            it.release()
            recorder = null
        }
        return nDuration
    }

    fun release() {
        try {
            recorder?.stop()
            recorder?.release()
            recorder = null
        } catch (ex: Exception) {
            ex.message?.toString()
        }
    }

    fun isRecording(): Boolean {
        return isRecording
    }

    fun getSaveFolder(): File? {
        return saveFolder
    }
}