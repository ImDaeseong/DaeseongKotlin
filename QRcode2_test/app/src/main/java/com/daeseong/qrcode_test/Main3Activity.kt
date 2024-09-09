package com.daeseong.qrcode_test

import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class Main3Activity : AppCompatActivity() {

    companion object {
        private val tag = Main3Activity::class.java.simpleName
    }

    private lateinit var previewView: PreviewView
    private lateinit var barcodeScanner: BarcodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        previewView = findViewById(R.id.previewView)

        // BarcodeScanner 초기화
        barcodeScanner = BarcodeScanning.getClient()

        // 카메라 시작
        startCamera()
    }

    private fun startCamera() {

        // 카메라 프로바이더 인스턴스 얻기
        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            try {

                // 카메라 프로바이더 인스턴스를 가져온 후 바인딩
                val cameraProvider = cameraProviderFuture.get()
                bindPreview(cameraProvider)

            } catch (e: Exception) {
                Log.e(tag, e.message ?: "Error initializing camera")
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @androidx.annotation.OptIn(ExperimentalGetImage::class)
    @OptIn(markerClass = [ExperimentalGetImage::class])
    private fun bindPreview(cameraProvider: ProcessCameraProvider) {

        // 미리보기 설정
        val preview = Preview.Builder().build()
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK) // 후면 카메라 선택
            .build()

        // 이미지 분석 설정
        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720)) // 해상도 설정
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // 최신 이미지만 유지
            .build()

        // 이미지 분석기 설정
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this)) { image ->
            val mediaImage = image.image
            if (mediaImage != null) {
                val inputImage = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)

                // 바코드 스캔 시작
                barcodeScanner.process(inputImage)
                    .addOnSuccessListener { barcodes ->
                        if (barcodes.isNotEmpty()) {

                            // 바코드 인식 성공
                            for (barcode in barcodes) {
                                val sText = barcode.rawValue
                                Log.e(tag, "Barcode: $sText")
                            }

                            // 인식 성공 시 카메라 멈춤
                            cameraProvider.unbindAll()
                        }
                    }
                    .addOnFailureListener { e -> Log.e(tag, e.message ?: "Error processing barcode") }
                    .addOnCompleteListener { image.close() } // 이미지 닫기
            }
        }

        // 기존 바인딩 해제
        cameraProvider.unbindAll()

        // 라이프사이클에 카메라 바인딩
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)

        // PreviewView에 SurfaceProvider 설정
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
}
