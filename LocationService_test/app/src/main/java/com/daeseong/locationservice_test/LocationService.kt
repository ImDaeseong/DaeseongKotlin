package com.daeseong.locationservice_test

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

class LocationService : Service(), LocationListener {

    companion object {
        var serviceIntent: Intent? = null
    }

    private val tag = LocationService::class.java.simpleName
    private val CHANNEL_ID = "LocationServiceChannel"
    private var locationManager: LocationManager? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        serviceIntent = intent

        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("서비스 실행중")
            .setContentText("위치 검색중...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)

        getLocation()

        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()

        serviceIntent = null

        locationManager?.let {
            try {
                it.removeUpdates(this)
            } catch (ex: SecurityException) {
                Log.e(tag, "onDestroy: ${ex.message}")
            }
        }
    }

    override fun onLocationChanged(location: Location) {

        Log.d(tag, "Location 변경: Lat ${location.latitude}, Lon ${location.longitude}")

        val intent = Intent("LOCATION_UPDATE")
        intent.putExtra("LATITUDE", location.latitude)
        intent.putExtra("LONGITUDE", location.longitude)
        sendBroadcast(intent)
    }

    override fun onProviderEnabled(provider: String) {
        Log.d(tag, "$provider enabled")
    }

    override fun onProviderDisabled(provider: String) {
        Log.d(tag, "$provider disabled")
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(CHANNEL_ID, "위치 서비스", NotificationManager.IMPORTANCE_DEFAULT)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    private fun getLocation() {

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(tag, "권한 없음")
            stopSelf()
            return
        }

        val isGpsEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled && !isNetworkEnabled) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            return
        }

        try {
            if (isNetworkEnabled) {
                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 1f, this)
                locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)?.let {
                    Log.d(tag, "Network Location: Lat ${it.latitude}, Lon ${it.longitude}")
                }
            }

            if (isGpsEnabled) {
                locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 1f, this)
                locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                    Log.d(tag, "GPS Location: Lat ${it.latitude}, Lon ${it.longitude}")
                }
            }
        } catch (ex: SecurityException) {
            Log.e(tag, "getLocation: ${ex.message}")
        }
    }
}
