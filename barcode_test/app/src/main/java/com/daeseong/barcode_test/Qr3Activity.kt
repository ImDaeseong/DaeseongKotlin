package com.daeseong.barcode_test

import android.os.Bundle
import android.os.Handler
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import me.dm7.barcodescanner.zxing.ZXingScannerView


class Qr3Activity : AppCompatActivity() {

    private val tag: String = Qr3Activity::class.java.simpleName

    private var zXingScannerView: ZXingScannerView? = null

    private var framelayout1: FrameLayout? = null
    private var textView1: TextView? = null

    private val handle: ZXingScannerView.ResultHandler = object : ZXingScannerView.ResultHandler {

        override fun handleResult(result: com.google.zxing.Result) {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr3)

        framelayout1 = findViewById<FrameLayout>(R.id.framelayout1)
        textView1 = findViewById<TextView>(R.id.textView1)

        zXingScannerView = ZXingScannerView(this)
        framelayout1!!.addView(zXingScannerView);
    }

    override fun onResume() {
        super.onResume()

        zXingScannerView!!.setResultHandler(handle)

        val formats: MutableList<BarcodeFormat> = ArrayList()
        formats.add(BarcodeFormat.CODE_128)
        formats.add(BarcodeFormat.QR_CODE)
        zXingScannerView!!.setFormats(formats)

        zXingScannerView!!.startCamera()
        zXingScannerView!!.setAutoFocus(true)
    }

    override fun onPause() {
        super.onPause()

        zXingScannerView!!.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()

        zXingScannerView!!.stopCamera()
    }
}

