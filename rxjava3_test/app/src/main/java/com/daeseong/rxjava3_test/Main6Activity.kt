package com.daeseong.rxjava3_test

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.rxjava3_test.Common.SendUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

class Main6Activity : AppCompatActivity() {

    companion object {
        private const val TAG = "Main6Activity"
        private const val PORT = 10000
        private const val LOCAL_IP_SUFFIX = ".2"
    }

    private lateinit var button1: Button
    private lateinit var textView1: TextView
    private var sLocalIP: String? = null
    private val sendUtil = SendUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        textView1 = findViewById(R.id.textView1)
        button1 = findViewById(R.id.button1)

        setLocalIP()

        button1.setOnClickListener {
            val sMsg = textView1.text.toString()
            if (sMsg.isNotBlank() && sLocalIP != null) {
                sendUtil.sendMessage(sLocalIP!!, PORT, sMsg)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ success ->
                        Log.d(TAG, "Success: $success")
                    }, { throwable ->
                        Log.e(TAG, "Error:", throwable)
                    })
            }
        }
    }

    private fun setLocalIP() {
        sLocalIP = getLocalIpAddress()?.let { ip ->
            val sArray = ip.split(".")
            "${sArray[0]}.${sArray[1]}.${sArray[2]}$LOCAL_IP_SUFFIX"
        }
        Log.d(TAG, "sLocalIP: $sLocalIP")
    }

    private fun getLocalIpAddress(): String? {
        return try {
            NetworkInterface.getNetworkInterfaces().asSequence()
                .flatMap { it.inetAddresses.asSequence() }
                .filter { !it.isLoopbackAddress && it is Inet4Address }
                .map { it.hostAddress }
                .firstOrNull()
        } catch (ex: SocketException) {
            Log.e(TAG, "SocketException", ex)
            null
        }
    }
}
