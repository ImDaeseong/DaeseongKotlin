package com.daeseong.networkcheck_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var tv1: TextView

    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)

        // 네트워크 변경 감지 리시버 등록
        networkChangeReceiver = registerNetworkReceiver(this) { isConnected ->

            if (isConnected) {

                val rssi = getWifiRssi(this)
                val signalLevel = getWifiSignalLevel(this)
                val isCommunicationPoor = rssi < -70

                //wifi
                /*
                    -30dBm ~ -50dBm: 매우 강한 신호
                    -51dBm ~ -60dBm: 강한 신호
                    -61dBm ~ -70dBm: 중간 신호
                    -71dBm ~ -80dBm: 약한 신호
                    -81dBm ~ -90dBm: 매우 약한 신호
                */

                if (isCommunicationPoor) {
                    tv1.text = "통신 불량 가능성 있음, RSSI 값: $rssi"
                } else {
                    tv1.text = "통신 양호, RSSI 값: $rssi"
                }
            } else {
                tv1.text = "네트워크 연결 끊김"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // 네트워크 변경 감지 리시버 해제
        unregisterNetworkReceiver(this, networkChangeReceiver)
    }


    // 네트워크 변경 감지 리시버 등록 함수
    private fun registerNetworkReceiver(context: Context, onNetworkChange: (Boolean) -> Unit): NetworkChangeReceiver {
        val receiver = NetworkChangeReceiver(onNetworkChange)
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(receiver, filter)
        return receiver
    }

    // 네트워크 변경 감지 리시버 해제 함수
    private fun unregisterNetworkReceiver(context: Context, receiver: NetworkChangeReceiver) {
        context.unregisterReceiver(receiver)
    }

    // 네트워크 상태 체크 함수
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

    // Wi-Fi RSSI 값 확인 함수
    private fun getWifiRssi(context: Context): Int {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo = wifiManager.connectionInfo
        return wifiInfo.rssi
    }

    // Wi-Fi 신호 강도 수준 확인 함수 (0에서 4 사이)
    private fun getWifiSignalLevel(context: Context): Int {
        val rssi = getWifiRssi(context)
        return WifiManager.calculateSignalLevel(rssi, 5)
    }

    // 네트워크 변경 감지 리시버 클래스
    class NetworkChangeReceiver(private val onNetworkChange: (Boolean) -> Unit) : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            val isConnected = activeNetwork != null && activeNetwork.isConnected
            onNetworkChange(isConnected)
        }
    }

}