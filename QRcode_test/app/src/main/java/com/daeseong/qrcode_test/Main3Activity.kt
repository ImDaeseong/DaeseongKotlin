package com.daeseong.qrcode_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import me.dm7.barcodescanner.zxing.ZXingScannerView

class Main3Activity : AppCompatActivity() {

    private val tag: String = Main3Activity::class.java.simpleName

    private var zXingScannerView: ZXingScannerView? = null

    private var cL1: ConstraintLayout? = null

    private val handle: ZXingScannerView.ResultHandler = object : ZXingScannerView.ResultHandler {

        override fun handleResult(result: com.google.zxing.Result) {

            val txt = result.text
            val sBarcodeFormatText = result.barcodeFormat.toString()
            Log.e(tag,"handleResult - sText : $txt sBarcodeFormatText: $sBarcodeFormatText")

            //한번 찍고 나서 멈추는걸 방지하기 위해
            zXingScannerView!!.resumeCameraPreview(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        cL1 = findViewById<ConstraintLayout>(R.id.cL1)

        initScanner()

        startScanner()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopScanner()
    }

    private fun initScanner() {
        zXingScannerView = ZXingScannerView(this)
        zXingScannerView!!.setResultHandler(handle)
        cL1!!.addView(zXingScannerView)
    }

    private fun startScanner() {
        zXingScannerView!!.startCamera()
    }

    private fun stopScanner() {
        zXingScannerView!!.stopCamera()
    }
}
