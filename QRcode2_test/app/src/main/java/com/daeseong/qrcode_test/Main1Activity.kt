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

    companion object {
        private val tag = Main1Activity::class.java.simpleName
    }

    private lateinit var iv1: ImageView
    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        iv1 = findViewById(R.id.iv1)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {
            val bitmap = createQRCode("0123456789-qr:코드읽기", 512, 512)
            bitmap?.let {
                iv1.setImageBitmap(it)
            }
        }
    }

    private fun createQRCode(message: String, width: Int, height: Int): Bitmap? {
        return try {
            val bitMatrix: BitMatrix = QRCodeWriter().encode(message, BarcodeFormat.QR_CODE, width, height)
            Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565).apply {
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(tag, e.message ?: "Error")
            null
        }
    }
}
