package com.daeseong.qrcode_test

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class Main2Activity : AppCompatActivity() {

    companion object {
        private val tag = Main2Activity::class.java.simpleName
    }

    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var fL1: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fL1 = findViewById(R.id.fL1)

        initScanner()
        startScanner()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopScanner()
    }

    private fun initScanner() {
        barcodeView = DecoratedBarcodeView(this)
        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    val sText = it.text
                    val sBarcodeFormatText = it.barcodeFormat.toString()
                    Log.e(tag, "sText: $sText, sBarcodeFormatText: $sBarcodeFormatText")

                    // QR 코드 스캔 종료
                    // stopScanner()
                }
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>?) {
                resultPoints?.forEach { point ->
                    Log.d(tag, "Possible Point: $point")
                }
            }
        })

        fL1.addView(barcodeView)
    }

    private fun startScanner() {
        barcodeView.resume()
    }

    private fun stopScanner() {
        barcodeView.pause()
    }
}
