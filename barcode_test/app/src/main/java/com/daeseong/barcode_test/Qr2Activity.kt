package com.daeseong.barcode_test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.dm7.barcodescanner.zxing.ZXingScannerView


class Qr2Activity : AppCompatActivity() {

    private val tag: String = Qr2Activity::class.java.simpleName

    private var zXingScannerView: ZXingScannerView? = null

    private val handle = ZXingScannerView.ResultHandler { result ->

        val txt = result.text
        val sBarcodeFormat = result.barcodeFormat.toString()

        Toast.makeText(this@Qr2Activity, txt, Toast.LENGTH_LONG).show()
        Toast.makeText(this@Qr2Activity, sBarcodeFormat, Toast.LENGTH_LONG).show()

        setContentView(R.layout.activity_qr2)

        zXingScannerView!!.stopCamera()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr2)
    }

    override fun onResume() {
        super.onResume()

        startScan()
    }

    override fun onPause() {
        super.onPause()

        zXingScannerView!!.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()

        zXingScannerView!!.stopCamera()
    }

    private fun startScan() {
        zXingScannerView = ZXingScannerView(this)
        zXingScannerView!!.setResultHandler(handle)
        setContentView(zXingScannerView)
        zXingScannerView!!.startCamera()
    }
}
