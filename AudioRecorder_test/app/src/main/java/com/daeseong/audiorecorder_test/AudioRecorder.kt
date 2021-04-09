package com.daeseong.audiorecorder_test

import android.content.Context
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException

class AudioRecorder {

    private val tag = AudioRecorder::class.java.name

    companion object {

        private var recorder: MediaRecorder? = null
        private var saveFolder: File? = null
        private var mContext: Context? = null
        private var isRecording = false
        private var recordingStart: Long = 0

        private var instance: AudioRecorder? = null
        fun getInstance(context: Context): AudioRecorder {
            if (instance == null) {
                instance = AudioRecorder()
            }
            return instance as AudioRecorder
        }
    }

    fun AudioRecorder(context: Context) {
        recorder = MediaRecorder()
        mContext = context
    }

    fun startRecord(sfilename: String): Boolean {
        return if (!isRecording) {

            val sFile = "$sfilename.aac"
            saveFolder = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), sFile)

            if (recorder == null) {
                recorder = MediaRecorder()
            }

            try {
                recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
                recorder!!.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
                recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                recorder!!.setOutputFile(saveFolder!!.absolutePath)
                recorder!!.prepare()
                recorder!!.start()
                recordingStart = System.currentTimeMillis()
                isRecording = true
                true
            } catch (iex: IOException) {
                iex.printStackTrace()
                false
            }
        } else {
            true
        }
    }

    fun stopRecord(): Int {
        var nDuration = 0

        if (recorder != null) {

            nDuration = ((System.currentTimeMillis() - recordingStart) / 1000).toInt()
            isRecording = false

            if (isRecording) {
                recorder!!.stop()
            }

            recorder!!.reset()
            recorder!!.release()
            recorder = null
        }
        return nDuration
    }

    fun release() {
        try {
            recorder!!.stop()
            recorder!!.release()
            recorder = null
        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    fun isRecording(): Boolean {
        return isRecording
    }

    fun getSaveFolder(): File? {
        return saveFolder
    }
}