package com.daeseong.createchooser_test

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button

    private val PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val PERMISSIONS33 = arrayOf(Manifest.permission.READ_MEDIA_IMAGES)

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

            val bImage: Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result[Manifest.permission.READ_MEDIA_IMAGES] == true
            } else {
                result[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
            }

            if (bImage) {
                Log.e(tag, "PERMISSIONS 권한 소유")
            } else {
                Log.e(tag, "PERMISSIONS 권한 미소유")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)

        button1.setOnClickListener {
            val sTitle = "제목1"
            val slink = "https://m.naver.com"
            ShareUtils.shareLink(this, sTitle, slink)
        }

        button2.setOnClickListener {
            val sTitle = "제목2"
            val slink = "https://m.naver.com"
            val sText = "사용 설명과 링크 정보1\n사용 설명과 링크 정보2\n$slink"
            ShareUtils.shareText(this, sTitle, sText)
        }

        button3.setOnClickListener {
            val sTitle = "제목3"
            val imgUri = ImgUtils.getAsset(this, "a.png")
            if (imgUri != null) {
                ShareUtils.shareImage(this, sTitle, imgUri)
            }
        }

        button4.setOnClickListener {
            val sTitle = "제목4"
            val slink = "https://m.naver.com"
            val sText = "사용 설명과 링크 정보1\n사용 설명과 링크 정보2\n$slink"
            val imgUri = ImgUtils.getAsset(this, "a.png")
            if (imgUri != null) {
                ShareUtils.shareMultiText(this, sTitle, sText, imgUri)
            }
        }

        button5.setOnClickListener {
            try {
                val sImgUrl =
                    "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
                ImgUtils.getObservableBitmap(sImgUrl)
                    .subscribe { bBitmap ->
                        if (bBitmap != null) {
                            val imgUri = ImgUtils.getUri(this, bBitmap)
                            val sTitle = "제목5"
                            val slink = "https://m.naver.com"
                            val sText = "사용 설명과 링크 정보1\n사용 설명과 링크 정보2\n$slink"
                            if (imgUri != null) {
                                ShareUtils.shareMultiText(this, sTitle, sText, imgUri)
                            }
                        }
                    }
            } catch (ex: Exception) {
            }
        }

        button6.setOnClickListener {
            openSettings(1)
        }

        checkPermissions()
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
                    Log.e(tag, "PERMISSIONS33 권한 소요")
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
                    Log.e(tag, "PERMISSIONS 권한 소요")
                }
            }
        }
    }

    private fun openSettings(settingType: Int) {

        val intent: Intent? = when (settingType) {
            1 -> Intent(Settings.ACTION_SETTINGS)
            2 -> Intent(Settings.ACTION_WIRELESS_SETTINGS)
            3 -> Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
            4 -> Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            5 -> Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
            6 -> Intent(Settings.ACTION_DISPLAY_SETTINGS)
            7 -> Intent(Settings.ACTION_LOCALE_SETTINGS)
            8 -> Intent(Settings.ACTION_DEVICE_INFO_SETTINGS)
            else -> null
        }

        intent?.let {
            startActivity(it)
        }
    }

}