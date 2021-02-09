package com.daeseong.qrcode_test

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import me.dm7.barcodescanner.zxing.ZXingScannerView


class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private var zXingScannerView: ZXingScannerView? = null

    private var fL1: FrameLayout? = null

    private val handle: ZXingScannerView.ResultHandler =  ZXingScannerView.ResultHandler { result ->

        val txt = result.text
        val sBarcodeFormatText = result.barcodeFormat.toString()

        stopScanner()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fL1 = findViewById<FrameLayout>(R.id.fL1)

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
        fL1!!.addView(zXingScannerView)
    }

    private fun startScanner() {
        zXingScannerView!!.startCamera()
    }

    private fun stopScanner() {
        zXingScannerView!!.stopCamera()
    }
}
