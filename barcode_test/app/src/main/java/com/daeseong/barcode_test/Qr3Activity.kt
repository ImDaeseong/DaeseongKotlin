package com.daeseong.barcode_test

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import me.dm7.barcodescanner.zxing.ZXingScannerView

class Qr3Activity : AppCompatActivity() {

    private val tag: String = Qr3Activity::class.java.simpleName

    private lateinit var zXingScannerView: ZXingScannerView
    private lateinit var framelayout1: FrameLayout
    private lateinit var textView1: TextView

    private val handle: ZXingScannerView.ResultHandler = object : ZXingScannerView.ResultHandler {

        override fun handleResult(result: com.google.zxing.Result) {
            val txt = result.text
            val sBarcodeFormat = result.barcodeFormat.toString()

            val sMsg = "$txt $sBarcodeFormat"
            textView1.text = sMsg

            Handler(Looper.getMainLooper()).postDelayed({
                //한번 찍고 나서 멈추는걸 방지하기 위해
                zXingScannerView.resumeCameraPreview(this)
            }, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr3)

        framelayout1 = findViewById(R.id.framelayout1)
        textView1 = findViewById(R.id.textView1)

        zXingScannerView = ZXingScannerView(this)
        framelayout1.addView(zXingScannerView)
    }

    override fun onResume() {
        super.onResume()

        zXingScannerView.setResultHandler(handle)

        val formats: MutableList<BarcodeFormat> = mutableListOf(BarcodeFormat.CODE_128, BarcodeFormat.QR_CODE)
        zXingScannerView.setFormats(formats)

        zXingScannerView.startCamera()
        zXingScannerView.setAutoFocus(true)
    }

    override fun onPause() {
        super.onPause()

        zXingScannerView.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()

        zXingScannerView.stopCamera()
    }
}

