package com.daeseong.rxjava3_test.Common

import io.reactivex.rxjava3.core.Observable
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class SendUtil {

    fun sendMessage(serverIP: String?, port: Int, sMsg: String): Observable<Boolean?>? {
        return Observable.fromCallable {
            try {
                DatagramSocket().use { datagramSocket ->
                    datagramSocket.broadcast = true
                    val address = InetAddress.getByName(serverIP)
                    val bytesend: ByteArray = sMsg.toByteArray()
                    val sendPacket = DatagramPacket(bytesend, bytesend.size, address, port)
                    datagramSocket.send(sendPacket)
                    return@fromCallable true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}
