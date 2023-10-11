package com.daeseong.locationservice_test

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button

    private var broadcastReceiver: BroadcastReceiver? = null

    private var currentLatitude = 0.0
    private var currentLongitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            runService()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            stopService()
        }

        if (broadcastReceiver == null) {
            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    currentLatitude = intent.getDoubleExtra("LATITUDE", 0.0)
                    currentLongitude = intent.getDoubleExtra("LONGITUDE", 0.0)
                    val sMsg = "currentLatitude $currentLatitude currentLongitude $currentLongitude"
                    Toast.makeText(this@MainActivity, sMsg, Toast.LENGTH_SHORT).show()
                }
            }
            registerReceiver(broadcastReceiver, IntentFilter("LOCATION_UPDATE"))
        }

        checkPermission()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopService()

        broadcastReceiver?.let {
            unregisterReceiver(it)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //Log.e(tag, "위치 권한 없음")
            } else {

                //Log.e(tag, "위치 권한 있음")
                runService()
            }
        }
    }

    private fun runService() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (LocationService.serviceIntent == null) {
                    val intent = Intent(this, LocationService::class.java)
                    startForegroundService(intent)
                } else {
                    Log.e(tag, "startForegroundService 이미 실행중")
                }
            } else {
                if (LocationService.serviceIntent == null) {
                    val intent = Intent(this, LocationService::class.java)
                    startService(intent)
                } else {
                    Log.e(tag, "startService 이미 실행중")
                }
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun stopService() {

        try {
            val intent = Intent(this, LocationService::class.java)
            stopService(intent)
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Log.e(tag, "위치 권한 없음")

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                //Log.e(tag, "사용자가 위치 권한 취소시 권한 재요청")
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            } else {

                //Log.e(tag, "최초로 위치 권한 요청 첫실행")
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            }
        } else {

            //Log.e(tag, "위치 권한 있음")
            runService()
        }
    }

}