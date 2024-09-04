package com.daeseong.rxjava3_test.Common

import io.reactivex.rxjava3.core.Observable
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class SendUtil {

    fun sendMessage(serverIP: String, port: Int, sMsg: String): Observable<Boolean> {
        return Observable.fromCallable {
            try {

                // 서버 IP
                val address = InetAddress.getByName(serverIP)

                // DatagramSocket을 use 블록으로 감싸 자동으로 자원 해제
                DatagramSocket().use { datagramSocket ->
                    datagramSocket.broadcast = true
                    val bytesend: ByteArray = sMsg.toByteArray()
                    val sendPacket = DatagramPacket(bytesend, bytesend.size, address, port)
                    datagramSocket.send(sendPacket)
                    true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}
