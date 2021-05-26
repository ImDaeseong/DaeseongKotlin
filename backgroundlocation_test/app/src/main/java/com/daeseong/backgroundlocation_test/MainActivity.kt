package com.daeseong.backgroundlocation_test

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null

    //브로드캐스트
    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null
    private var currentLatitude = 0.0
    private var currentLongitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //브로드캐스트 등록
        initFilter()

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            requsetPermission()
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            //Log.e(tag, "stopService 중지")
            stopService()
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        //브로드캐스트 해제
        DestoryFilter()
    }

    private fun initFilter() {

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                currentLatitude = intent.getDoubleExtra("LATITUDE", 0.0)
                currentLongitude = intent.getDoubleExtra("LONGITUDE", 0.0)
                //Log.e(tag, "onReceive currentLatitude: $currentLatitude")
                //Log.e(tag, "onReceive currentLongitude: $currentLongitude")

                Handler(Looper.getMainLooper()).postDelayed({
                    val sMsg = String.format("currentLatitude %f currentLongitude %f", currentLatitude,  currentLongitude)
                    Toast.makeText(this@MainActivity, sMsg, Toast.LENGTH_SHORT).show()
                }, 100)

            }
        }

        intentFilter = IntentFilter()
        intentFilter!!.addAction("com.daeseong.backgroundlocation_test.Location")
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun DestoryFilter() {
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver)
        }
    }

    private fun runService() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                if (LocationService.serviceIntent == null) {
                    val intent = Intent(this, LocationService::class.java)
                    startForegroundService(intent)
                }

            } else {
                if (LocationService.serviceIntent == null) {
                    val intent = Intent(this, LocationService::class.java)
                    startService(intent)
                }
            }
        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    private fun stopService() {
        try {
            val intent = Intent(this, LocationService::class.java)
            stopService(intent)
        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (checkBackgroundLocation()) {

                    //Log.e(tag, "ACCESS_BACKGROUND_LOCATION 권한 있음 1")
                    runService()

                } else {

                    //Log.e(tag, "ACCESS_BACKGROUND_LOCATION 권한 체크")
                    requestBackgrundLocation()
                }
            }
        } else if (requestCode == 2) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Log.e(tag, "ACCESS_BACKGROUND_LOCATION 권한 있음 2")
                runService()
            }
        }
    }

    private fun requsetPermission() {

        if (checkFindLocation() && checkCoarseLocation()) {

            if (checkBackgroundLocation()) {

                //Log.e(tag, "ACCESS_BACKGROUND_LOCATION 권한 있으므로 runService 실행")
                runService()
            } else {

                //Log.e(tag, "ACCESS_BACKGROUND_LOCATION 권한 요청")
                requestBackgrundLocation()
            }

        } else {

            //Log.e(tag, "ACCESS_FINE_LOCATION 권한 요청")
            requestFineLocation()
        }
    }

    private fun checkBackgroundLocation(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun checkFindLocation(): Boolean {
        return ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkCoarseLocation(): Boolean {
        return ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestFineLocation() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
    }

    private fun requestBackgrundLocation() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),2)
    }
}