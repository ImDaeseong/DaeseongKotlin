package com.daeseong.audiorecorder_test

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.daeseong.audiorecorder_test.AudioPlayer.OnMediaPlayerListener

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private val permissions = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null

    private var audioRecorder: AudioRecorder? = null
    private var audioPlayer: AudioPlayer? = null
    private var length = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioRecorder = AudioRecorder.getInstance(this)
        audioPlayer = AudioPlayer.getInstance(this)

        checkPermissions()

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            if (audioRecorder!!.isRecording()) {

                length = audioRecorder!!.stopRecord()
                audioRecorder!!.release()
                Log.e(tag, "녹음이 완료되었습니다. length:$length")
            } else {

                audioRecorder!!.startRecord("katakata")
                Log.e(tag, "녹음을 시작합니다.")
            }
        }

        button2 = findViewById<View>(R.id.button2) as Button
        button2!!.setOnClickListener {

            if (length > 0) {

                val sFilename = audioRecorder!!.getSaveFolder()!!.absoluteFile.toString()
                Log.e(tag, "sFilename:$sFilename length:$length")

                if (audioRecorder!!.getSaveFolder() != null) {

                    val file = audioRecorder!!.getSaveFolder()!!.absoluteFile
                    if (file.delete()) {
                        Log.e(tag, "파일이 삭제 되었습니다.")
                    }
                }
            }
        }

        button3 = findViewById<View>(R.id.button3) as Button
        button3!!.setOnClickListener(View.OnClickListener {

            val sRedordPath = audioRecorder!!.getSaveFolder()!!.absoluteFile.path
            if (sRedordPath!!.isEmpty()) {
                Log.e(tag, "녹음된 파일을 찾을 수 없습니다.")
                return@OnClickListener
            }

            audioPlayer!!.play(sRedordPath, object : OnMediaPlayerListener {
                override fun onCompletion(bComplete: Boolean) {
                    Log.e(tag, "녹음된 내용을 전부 들었습니다.")
                }

                override fun onPrepared(mDuration: Int) {
                    Log.e(tag, "녹음된 파일이 준비가 되었습니다.")
                }
            })
        })

        button4 = findViewById<View>(R.id.button4) as Button
        button4!!.setOnClickListener {
            if (audioPlayer!!.isPlaying()) {

                Log.e(tag, "녹음된 내용 플레이 중지")
                audioPlayer!!.pause()
            } else {

                Log.e(tag, "녹음된 내용 플레이 시작")
                audioPlayer!!.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (audioRecorder!!.isRecording()) {
            audioRecorder!!.stopRecord()
        }

        audioPlayer!!.release()
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    private fun checkPermissions() {
        if (!hasPermissions(this, *permissions)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, permissions, 1)
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(tag, "권한이 승인됨 상태:" + permissions[i])
                } else {
                    Log.e(tag, "권한이 승인되지 않음 상태:" + permissions[i])
                }
            }
        }
    }
}