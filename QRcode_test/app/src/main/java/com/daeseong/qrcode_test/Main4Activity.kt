package com.daeseong.qrcode_test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.core.ViewFinderView
import me.dm7.barcodescanner.zxing.ZXingScannerView

class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName

    private lateinit var zXingScannerView: ZXingScannerView
    private var cL1: ConstraintLayout? = null

    private val handle: ZXingScannerView.ResultHandler = object : ZXingScannerView.ResultHandler {
        override fun handleResult(result: com.google.zxing.Result) {

            val txt = result.text
            val sBarcodeFormatText = result.barcodeFormat.toString()
            Log.e(tag,"handleResult - sText : $txt sBarcodeFormatText: $sBarcodeFormatText")

            Handler(Looper.getMainLooper()).postDelayed({
                // 한 번 찍고 나서 멈추는 걸 방지하기 위해
                zXingScannerView.resumeCameraPreview(this)
            }, 2000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        cL1 = findViewById(R.id.cL1)
        initScanner()
        startScanner()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopScanner()
    }

    private fun initScanner() {
        zXingScannerView = object : ZXingScannerView(this) {
            override fun createViewFinderView(context: Context?): IViewFinder? {
                return CustomViewFinderView(context)
            }
        }
        zXingScannerView.setResultHandler(handle)
        cL1!!.addView(zXingScannerView)
    }

    private fun startScanner() {
        zXingScannerView.startCamera()
    }

    private fun stopScanner() {
        zXingScannerView.stopCamera()
    }
}

private class CustomViewFinderView : ViewFinderView {

    private val TRADE_MARK_TEXT = "QR 코드 인식"
    private val TRADE_MARK_TEXT_SIZE_SP = 30

    private val paint: Paint = Paint()

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        val textPixelSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, TRADE_MARK_TEXT_SIZE_SP.toFloat(), resources.displayMetrics)
        paint.textSize = textPixelSize
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawTradeMark(canvas)
    }

    private fun drawTradeMark(canvas: Canvas) {
        val framingRect: Rect? = framingRect
        val tradeMarkTop: Float
        val tradeMarkLeft: Float
        if (framingRect != null) {
            tradeMarkTop = framingRect.bottom + paint.textSize + 10
            tradeMarkLeft = framingRect.left.toFloat()
        } else {
            tradeMarkTop = 10f
            tradeMarkLeft = canvas.height - paint.textSize - 10
        }
        canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, paint)
    }
}
