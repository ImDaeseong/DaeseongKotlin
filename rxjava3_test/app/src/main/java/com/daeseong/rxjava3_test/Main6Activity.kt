package com.daeseong.rxjava3_test

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.rxjava3_test.Common.SendUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var textView1: TextView
    private var sLocalIP: String? = null
    private val sendUtil: SendUtil = SendUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        setLocalIP()

        textView1 = findViewById(R.id.textView1)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {
            val sMsg = textView1.text.toString()
            if (TextUtils.isEmpty(sMsg)) return@setOnClickListener

            sendUtil.sendMessage(sLocalIP, 10000, sMsg)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.onErrorComplete()
                ?.subscribe(Consumer { bResult: Boolean? ->
                    try {
                        Log.e(tag, bResult.toString())
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                })
        }
    }

    private fun setLocalIP() {
        try {
            val sIP: String = getLocalIpAddress() ?: return
            val sArray = sIP.split("\\.").toTypedArray()
            sLocalIP = "${sArray[0]}.${sArray[1]}.${sArray[2]}.2"
            Log.e(tag, "sLocalIP: $sLocalIP")
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun getLocalIpAddress(): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.hostAddress
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }
}
