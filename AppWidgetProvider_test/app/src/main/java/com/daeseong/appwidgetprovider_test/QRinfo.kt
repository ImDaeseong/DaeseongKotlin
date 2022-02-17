package com.daeseong.appwidgetprovider_test

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

object QRinfo {

    private val TAG = QRinfo::class.java.simpleName

    fun CreateQRrcode(sMessgae: String?, nwidth: Int, nheight: Int): Bitmap? {

        val qrCodeWriter = QRCodeWriter()
        var bitMatrix: BitMatrix? = null
        var bitmap: Bitmap? = null

        try {

            bitMatrix = qrCodeWriter.encode(sMessgae, BarcodeFormat.QR_CODE, nwidth, nheight)
            val width = bitMatrix.width
            val height = bitMatrix.height

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
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