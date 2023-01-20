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

    private var iv1: ImageView? = null
    private var button1 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        iv1 = findViewById<ImageView>(R.id.iv1)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            val bitmap = CreateQRrcode("0123456789-qr:코드읽기", 512, 512)
            if (bitmap != null) {
                iv1!!.setImageBitmap(bitmap)
            }
        }
    }

    private fun CreateQRrcode(sMessgae: String, nwidth: Int, nheight: Int): Bitmap? {

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
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
        return bitmap
    }

}
