package com.im.daeseong.tableapp_test1

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.im.daeseong.tableapp_test1.Control.LoadingDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.simpleName

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        getAppVersion()
        getOSVersion()
        getANDROID()
        getDeviceName()

        getDeviceInfo(this)
        getHardwareInfo()
        getDrawableInfo()
        getDimsInfo()

        if (isTablet(this)) {
            Log.d(tag, "태블릿")
        } else {
            Log.d(tag, "스마트폰")
        }

        loadingDialog = LoadingDialog(this)
        showLoading()
    }

    override fun onDestroy() {
        super.onDestroy()

        hideLoading()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Log.d(tag, "가로 모드에서 레이아웃 변경")

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            Log.d(tag, "세로 모드에서 레이아웃 변경")
        }

        Log.d(tag, "디스플레이 해상도: ${newConfig.screenWidthDp} x ${newConfig.screenHeightDp}")
    }

    private fun getAppVersion()  {

        val versionName: String = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, 0).versionName.toString()
        } else {
            val flags = PackageManager.GET_SIGNING_CERTIFICATES
            packageManager.getPackageInfo(packageName, flags).versionName.toString()
        }
        Log.d(tag, "앱 버전: $versionName")
    }

    private fun getOSVersion() {
        val osVerSion = "Android : " + Build.VERSION.RELEASE
        Log.d(tag, "안드로이드 OS: $osVerSion")
    }

    private fun getANDROID() {
        val androidID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        Log.d(tag, "IMEI: $androidID")
    }

    private fun getDeviceName() {

        val manufacturer = Build.MANUFACTURER.replaceFirstChar { it.uppercase() }
        val model = Build.MODEL
        val deviceName = if (model.startsWith(manufacturer, ignoreCase = true)) {
            model.replaceFirstChar { it.uppercase() }
        } else {
            "$manufacturer $model"
        }
        Log.e(tag, "deviceName : $deviceName")
    }

    private fun getDeviceInfo(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = context.getSystemService(WindowManager::class.java).currentWindowMetrics
            val bounds = windowMetrics.bounds
            val realWidth = bounds.width()
            val realHeight = bounds.height()
            Log.d(tag, "디스플레이 해상도: ${realWidth} x ${realHeight}")
        }
    }

    private fun getHardwareInfo() {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        val device = Build.DEVICE
        val product = Build.PRODUCT
        val sdkVersion = Build.VERSION.SDK_INT

        Log.d(tag, "제조사: $manufacturer")
        Log.d(tag, "모델명: $model")
        Log.d(tag, "디바이스: $device")
        Log.d(tag, "제품명: $product")
        Log.d(tag, "Android SDK: $sdkVersion")
    }

    private fun getDrawableInfo() {

        val bg: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg)
        val icon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon)

        val bgWidth = bg.width
        val bgHeight = bg.height

        val iconWidth = icon.width
        val iconHeight = icon.height

        Log.d(tag, "bg Size: ${bgWidth}x${bgHeight}")
        Log.d(tag, "icon Size: ${iconWidth}x${iconHeight}")
    }

    private fun getDimsInfo() {

        val textSize = resources.getDimension(R.dimen.text_size)
        val padding = resources.getDimension(R.dimen.padding)
        val imageSize = resources.getDimension(R.dimen.image_size)
        val bgImageSize = resources.getDimension(R.dimen.bgimage_size)

        Log.d(tag, "텍스트 크기: $textSize")
        Log.d(tag, "패딩 값: $padding")
        Log.d(tag, "이미지 크기: $imageSize")
        Log.d(tag, "배경 이미지 크기: $bgImageSize")
    }

    private fun isTablet(context: Context): Boolean {

        val config = context.resources.configuration

        return config.smallestScreenWidthDp >= 600 ||
                (config.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK in
                        listOf(Configuration.SCREENLAYOUT_SIZE_LARGE, Configuration.SCREENLAYOUT_SIZE_XLARGE))
    }

    private fun showLoading() {

        if(!loadingDialog.isShowing()){
            loadingDialog.show()
        }
    }

    private fun hideLoading() {

        lifecycleScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss()
                }
            }
        }
    }
}