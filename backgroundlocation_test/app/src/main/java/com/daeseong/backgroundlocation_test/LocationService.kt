package com.daeseong.backgroundlocation_test

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class LocationService : Service(), LocationListener {

    companion object {
        var serviceIntent: Intent? = null
    }

    private val tag = LocationService::class.java.simpleName
    private val CHANNEL_ID = "backgroundlocation_testLocationService"
    private var locationManager: LocationManager? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        serviceIntent = intent

        try {

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

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }

        getLocation()

        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()

        serviceIntent = null

        if (locationManager != null) {

            try {
                locationManager!!.removeUpdates(this)
            } catch (ex: Exception) {
                Log.e(tag, ex.message.toString())
            }
        }
    }

    override fun onLocationChanged(lastLocation: Location) {

        try {

            val item = Intent("com.daeseong.backgroundlocation_test.Location")
            item.putExtra("LATITUDE", lastLocation.latitude)
            item.putExtra("LONGITUDE", lastLocation.longitude)
            applicationContext.sendBroadcast(item)
            //onDestroy()
        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_ID,"내위치 정보", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun getLocation() {

        try {

            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val bGPS = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) //GPS 이용 위치,  android.permission.ACCESS_FINE_LOCATION
            val bNetwork = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER) //기지국, WIFI, android.permission.ACCESS_FINE_LOCATION||android.permission.ACCESS_COARSE_LOCATION

            if (!bGPS && !bNetwork) {

                //Log.e(TAG, "getLocation GPS Network 둘다 미사용")

                val item = Intent("com.daeseong.backgroundlocation_test.Location")
                item.putExtra("LATITUDE", 0)
                item.putExtra("LONGITUDE", 0)
                applicationContext.sendBroadcast(item)
                return
            }

            if (bNetwork) {

                locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0f,this)
                val lastLocation = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (lastLocation != null) {

                    val item = Intent("com.daeseong.backgroundlocation_test.Location")
                    item.putExtra("LATITUDE", lastLocation.latitude)
                    item.putExtra("LONGITUDE", lastLocation.longitude)
                    applicationContext.sendBroadcast(item)
                    //onDestroy()
                }

            } else {

                //onLocationChanged 에서 받을수 있음, 실내에서는 테스트 불가 , 실외에서 테스트 필요
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0f,this)
                val lastLocation = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (lastLocation != null) {

                    val item = Intent("com.daeseong.backgroundlocation_test.Location")
                    item.putExtra("LATITUDE", lastLocation.latitude)
                    item.putExtra("LONGITUDE", lastLocation.longitude)
                    applicationContext.sendBroadcast(item)
                    //onDestroy()
                }
            }
        } catch (ex: SecurityException) {
        }
    }
}