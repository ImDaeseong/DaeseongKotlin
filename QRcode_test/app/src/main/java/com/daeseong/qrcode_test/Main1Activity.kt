package com.daeseong.qrcode_test

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private lateinit var iv1: ImageView
    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        iv1 = findViewById(R.id.iv1)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {
            val bitmap = createQRCode("0123456789-qr:코드읽기", 512, 512)
            iv1.setImageBitmap(bitmap)
        }
    }

    private fun createQRCode(message: String, width: Int, height: Int): Bitmap? {
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
                    bitmap?.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }

        return bitmap
    }
}
