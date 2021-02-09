package com.daeseong.barcode_test

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.*
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.*


class Qr4Activity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val tag: String = Qr4Activity::class.java.simpleName

    private var zXingScannerView: ZXingScannerView? = null

    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null

    private var textView1: TextView? = null

    private var sBarcode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr4)

        textView1 = findViewById<TextView>(R.id.textView1)

        imageView1 = findViewById<ImageView>(R.id.imageView1)
        imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView3 = findViewById<ImageView>(R.id.imageView3)

        sBarcode = "ddsfasf123213441312349780"//"adQAd123DBV6"//"aAbcede"//"1234567890"
        displayCode1(sBarcode!!);
        displayCode2(sBarcode!!);
        displayCode3(sBarcode!!);
    }

    override fun onResume() {
        super.onResume()

        //startCode()
    }

    override fun onPause() {
        super.onPause()

        try {
            zXingScannerView!!.stopCamera()
        } catch (e: Exception) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            zXingScannerView!!.stopCamera()
        } catch (e: java.lang.Exception) {
        }
    }

    //com.journeyapps.barcodescanner.BarcodeEncoder
    private fun displayCode1(sCode: String) {

        val writer = MultiFormatWriter()
        try {
            val bitMatrix = writer.encode(sCode, BarcodeFormat.CODE_128, 300, 100)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            imageView1!!.setImageBitmap(bitmap)
        } catch (e: WriterException) {
        }
    }

    //encodeBitmap1
    private fun displayCode2(sCode: String) {

        try {
            val bitmap: Bitmap = encodeBitmap1(sCode, BarcodeFormat.CODE_128, 300, 100)!!
            imageView2!!.setImageBitmap(bitmap)
        } catch (e: WriterException) {
        }
    }

    @Throws(WriterException::class)
    private fun encodeBitmap1(sCode: String, format: BarcodeFormat, nWidht: Int, nHeight: Int): Bitmap? {

        val WHITE = -0x1
        val BLACK = -0x1000000
        val contentsToEncode = sCode ?: return null
        var hints: MutableMap<EncodeHintType?, Any?>? = null
        val encoding = guessAppropriateEncoding(contentsToEncode)
        if (encoding != null) {
            hints = EnumMap(EncodeHintType::class.java)
            hints[EncodeHintType.CHARACTER_SET] = encoding
        }

        val writer = MultiFormatWriter()
        val result: BitMatrix
        result = try {
            writer.encode(contentsToEncode, format, nWidht, nHeight, hints)
        } catch (iae: IllegalArgumentException) {
            // Unsupported format
            return null
        }

        val width = result.width
        val height = result.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result[x, y]) BLACK else WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    private fun guessAppropriateEncoding(str: CharSequence): String? {

        for (element in str) {
            if (element.toInt() > 0xff) {
                return "UTF-8"
            }
        }
        return null
    }

    //encodeBitmap2
    private fun displayCode3(sCode: String) {
        try {
            val bitmap = encodeBitmap2(sCode)
            imageView3!!.setImageBitmap(bitmap)
        } catch (e: java.lang.Exception) {
        }
    }

    private fun encodeBitmap2(sCode: String): Bitmap {

        try {
            val result = MultiFormatWriter().encode(sCode, BarcodeFormat.CODE_128, 300, 100, null)
            val w = result.width
            val h = result.height
            val pixels = IntArray(w * h)
            for (y in 0 until h) {
                val offset = y * w
                for (x in 0 until w) {
                    pixels[offset + x] = if (result[x, y]) Color.BLACK else Color.WHITE
                }
            }
            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, 300, 0, 0, w, h)
            return bitmap
        } catch (e: java.lang.Exception) {
        }
        return null!!
    }

    private fun startCode() {
        zXingScannerView = ZXingScannerView(applicationContext)
        setContentView(zXingScannerView)
        zXingScannerView!!.setResultHandler(this)
        zXingScannerView!!.startCamera()
    }

    override fun handleResult(result: Result) {

        val txt = result.text
        val sBarcodeFormat = result.barcodeFormat.toString()

        val sMsg = java.lang.String.format("%s %s", txt, sBarcodeFormat)
        textView1!!.text = sMsg

        Handler().postDelayed({

            //한번 찍고 나서 멈추는걸 방지하기 위해
            zXingScannerView!!.resumeCameraPreview(this);
        }, 1000)

    }

}
