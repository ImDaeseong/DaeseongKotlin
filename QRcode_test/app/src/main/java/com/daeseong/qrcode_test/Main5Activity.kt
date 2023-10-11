package com.daeseong.qrcode_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class Main5Activity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val tag = Main5Activity::class.java.simpleName
    private lateinit var zXingScannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        zXingScannerView = findViewById(R.id.zxsv)
        startScanner()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopScanner()
    }

    private fun startScanner() {
        zXingScannerView.setResultHandler(this)
        zXingScannerView.startCamera()
    }

    private fun stopScanner() {
        zXingScannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        val sText = rawResult?.text
        val sBarcodeFormatText = rawResult?.barcodeFormat.toString()

        Log.e(tag, "handleResult - sText : $sText sBarcodeFormatText: $sBarcodeFormatText")

        //한번 찍고 나서 멈추는걸 방지하기 위해
        zXingScannerView.resumeCameraPreview(this)
    }
}