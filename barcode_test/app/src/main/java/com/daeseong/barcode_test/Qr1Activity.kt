package com.daeseong.barcode_test

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class Qr1Activity : AppCompatActivity() , ZXingScannerView.ResultHandler {

    private val tag: String = Qr1Activity::class.java.simpleName

    private var zXingScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr1)

        zXingScannerView = ZXingScannerView(this)
        setContentView(zXingScannerView)


        checkPermission()
    }

    override fun handleResult(result: Result) {

        Toast.makeText(this, result.text,Toast.LENGTH_LONG).show()
        Toast.makeText(this, result.barcodeFormat.toString(),Toast.LENGTH_LONG).show()

        //한번 찍고 나서 멈추는걸 방지하기 위해
        zXingScannerView!!.resumeCameraPreview(this)

        finish()
    }


    override fun onResume() {
        super.onResume()

        if ( checkPermission() ) {

            if( zXingScannerView == null) {
                zXingScannerView = ZXingScannerView(this);
                setContentView(zXingScannerView)
            }

            zXingScannerView!!.setResultHandler(this)
            zXingScannerView!!.startCamera()
        }
    }

    override fun onPause() {
        super.onPause()

        zXingScannerView!!.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()

        zXingScannerView!!.stopCamera()
    }

    private fun checkPermission(): Boolean {

        //마시멜로 이상일 경우만 권한 체크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //권한이 없는 경우 요청
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode === 1) {

            if (grantResults.isNotEmpty()) {

                val result = grantResults[0] === PackageManager.PERMISSION_GRANTED
                if (!result) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                        }
                    }
                }

            }
        }
    }

}
