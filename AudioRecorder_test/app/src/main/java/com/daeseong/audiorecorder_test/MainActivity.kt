package com.daeseong.audiorecorder_test

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.audiorecorder_test.AudioPlayer.OnMediaPlayerListener

class MainActivity : AppCompatActivity() {

    private companion object {
        private val tag = MainActivity::class.java.name
    }

    private lateinit var requestPermissions: ActivityResultLauncher<Array<String>>

    private val PERMISSIONS = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val PERMISSIONS33 = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_MEDIA_AUDIO)

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private var audioRecorder: AudioRecorder? = null
    private var audioPlayer: AudioPlayer? = null
    private var length = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioRecorder = AudioRecorder.getInstance(this)
        audioPlayer = AudioPlayer.instance

        initPermissionsLauncher()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (audioRecorder!!.isRecording()) {
                length = audioRecorder!!.stopRecord()
                audioRecorder!!.release()
                Log.e(tag, "녹음이 완료되었습니다. length:$length")
            } else {
                audioRecorder!!.startRecord("katakata")
                Log.e(tag, "녹음을 시작합니다.")
            }
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {

            if (length > 0) {
                val sFilename = audioRecorder!!.getSaveFolder()?.absolutePath
                Log.e(tag, "sFilename:$sFilename length:$length")
                audioRecorder!!.getSaveFolder()?.let {
                    if (it.delete()) {
                        Log.e(tag, "파일이 삭제 되었습니다.")
                    }
                }
            }
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {

            val sRecordPath = audioRecorder!!.getSaveFolder()?.absolutePath
            if (sRecordPath.isNullOrEmpty()) {
                Log.e(tag, "녹음된 파일을 찾을 수 없습니다.")
                return@setOnClickListener
            }

            audioPlayer!!.play(sRecordPath, object : OnMediaPlayerListener {
                override fun onCompletion(bComplete: Boolean) {
                    Log.e(tag, "녹음된 내용을 전부 들었습니다.")
                }

                override fun onPrepared(mDuration: Int) {
                    Log.e(tag, "녹음된 파일이 준비가 되었습니다.")
                }
            })
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {

            if (audioPlayer!!.isPlaying()) {
                Log.e(tag, "녹음된 내용 플레이 중지")
                audioPlayer!!.pause()
            } else {
                Log.e(tag, "녹음된 내용 플레이 시작")
                audioPlayer!!.start()
            }
        }

        checkPermissions()
    }

    override fun onDestroy() {
        super.onDestroy()

        audioRecorder?.let {
            if (it.isRecording()) {
                try {
                    it.stopRecord()
                } catch (ex: Exception) {
                    Log.e(tag, ex.message.toString())
                }
            }
        }
        audioPlayer?.release()
    }

    private fun initPermissionsLauncher() {

        requestPermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    val bRecord = result[Manifest.permission.RECORD_AUDIO] == true
                    val bAudio = result[Manifest.permission.READ_MEDIA_AUDIO] == true

                    if (bRecord && bAudio) {
                        Log.e(tag, "PERMISSIONS 권한 소유")
                    } else {
                        Log.e(tag, "PERMISSIONS 권한 미소유")
                    }

                } else {

                    val bRecord = result[Manifest.permission.RECORD_AUDIO] == true
                    val bAudio = result[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true

                    if (bRecord && bAudio) {
                        Log.e(tag, "PERMISSIONS 권한 소유")
                    } else {
                        Log.e(tag, "PERMISSIONS 권한 미소유")
                    }
                }
            }
    }

    private fun checkPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            var bPermissResult = false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                for (permission in PERMISSIONS33) {
                    bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
                    if (!bPermissResult) {
                        break
                    }
                }

                if (!bPermissResult) {
                    requestPermissions.launch(PERMISSIONS33)
                } else {
                    Log.e(tag, "PERMISSIONS33 권한 소유")
                }

            } else {

                for (permission in PERMISSIONS) {
                    bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
                    if (!bPermissResult) {
                        break
                    }
                }

                if (!bPermissResult) {
                    requestPermissions.launch(PERMISSIONS)
                } else {
                    Log.e(tag, "PERMISSIONS 권한 소유")
                }
            }
        }
    }
}