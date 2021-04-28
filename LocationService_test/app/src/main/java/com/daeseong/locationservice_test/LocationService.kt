package com.daeseong.locationservice_test

import android.app.*
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat

class LocationService : Service(), LocationListener {

    companion object {
        var serviceIntent: Intent? = null
    }

    private val tag = LocationService::class.java.simpleName
    private val CHANNEL_ID = "LocationService"
    private var locationManager: LocationManager? = null

    override fun onBind(intent: Intent): IBinder? {
        Log.e(tag, "onBind")
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.e(tag, "onStartCommand")

        serviceIntent = intent

        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("")
            .setContentText("")
            .build()
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
        startForeground(1, notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true)
        } else {
            stopSelf()
        }

        getLocation()

        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        Log.e(tag, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e(tag, "onDestroy")

        serviceIntent = null

        if (locationManager != null) {
            try {
                locationManager!!.removeUpdates(this)
            } catch (ex: Exception) {
                Log.e(tag, ex.message.toString())
            }
        }
    }

    override fun onLocationChanged(location: Location) {

        Log.e(tag, "onLocationChanged getLatitude: " + location.latitude)
        Log.e(tag, "onLocationChanged getLongitude: " + location.longitude)

        val intent = Intent("LOCATION_UPDATE")
        intent.putExtra("LATITUDE", location.latitude)
        intent.putExtra("LONGITUDE", location.longitude)
        sendBroadcast(intent)
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //name 에 값이 없으면 않됨
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "LocationService NotificationChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun getLocation() {

        try {

            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val bGPS = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) //GPS 이용 위치,  android.permission.ACCESS_FINE_LOCATION
            val bNetwork = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER) //기지국, WIFI, android.permission.ACCESS_FINE_LOCATION||android.permission.ACCESS_COARSE_LOCATION

            Log.e(tag, "bGPS:$bGPS")
            Log.e(tag, "bNetwork:$bNetwork")

            if (!bGPS && !bNetwork) {

                //위치 사용 설정
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                applicationContext.startActivity(intent)
                return
            }

            if (bNetwork) {

                locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,1f,this)
                val location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (location != null) {
                    Log.e(tag, "Network getLatitude: " + location.latitude)
                    Log.e(tag, "Network getLongitude: " + location.longitude)
                }
            } else {

                //onLocationChanged 에서 받을수 있음, 실내에서는 테스트 불가 , 실외에서 테스트 필요
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,1f,this)
                val location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (location != null) {
                    Log.e(tag, "GPS getLatitude: " + location.latitude)
                    Log.e(tag, "GPS getLongitude: " + location.longitude)
                }
            }

        } catch (ex: SecurityException) {
            Log.e(tag, ex.message.toString())
        }
    }

}