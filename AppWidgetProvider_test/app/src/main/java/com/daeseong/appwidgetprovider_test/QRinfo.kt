package com.daeseong.appwidgetprovider_test

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

object QRinfo {

    private val TAG = QRinfo::class.java.simpleName

    fun createQRCode(message: String?, width: Int, height: Int): Bitmap? {
        val qrCodeWriter = QRCodeWriter()
        var bitMatrix: BitMatrix? = null
        var bitmap: Bitmap? = null

        try {
            bitMatrix = qrCodeWriter.encode(message, BarcodeFormat.QR_CODE, width, height)
            val matrixWidth = bitMatrix.width
            val matrixHeight = bitMatrix.height

            bitmap = Bitmap.createBitmap(matrixWidth, matrixHeight, Bitmap.Config.RGB_565)

            for (x in 0 until matrixWidth) {
                for (y in 0 until matrixHeight) {
                    bitmap.setPixel(
                        x,
                        y,
                        if (bitMatrix[x, y]) Color.BLACK else Color.rgb(219, 250, 170)
                    )
                }
            }

        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
        return bitmap
    }
}
