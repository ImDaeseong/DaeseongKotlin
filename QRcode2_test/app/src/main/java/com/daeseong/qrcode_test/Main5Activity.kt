package com.daeseong.qrcode_test

import android.os.Bundle
import android.util.Log
import androidx.annotation.OptIn
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
import java.util.concurrent.ExecutionException

class Main5Activity : AppCompatActivity() {

    companion object {
        private val tag = Main5Activity::class.java.simpleName
    }

    private lateinit var previewView: PreviewView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        previewView = findViewById(R.id.preview_view)

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

            } catch (e: ExecutionException) {
                Log.e(tag, e.message ?: "ExecutionException")
            } catch (e: InterruptedException) {
                Log.e(tag, e.message ?: "InterruptedException")
            }
        }, ContextCompat.getMainExecutor(this)) // 메인 스레드에서 실행
    }

    @OptIn(markerClass = [ExperimentalGetImage::class])
    private fun bindPreview(cameraProvider: ProcessCameraProvider) {

        // 미리보기 설정
        val preview = Preview.Builder().build()

        // 후면 카메라 선택
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        // PreviewView에 SurfaceProvider 설정
        preview.setSurfaceProvider(previewView.surfaceProvider)

        // 이미지 분석 설정
        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // 최신 이미지 유지
            .build()

        // 이미지 분석기 설정
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this)) { imageProxy ->
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                // 이미지에서 InputImage 생성
                val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                // ML Kit 바코드 스캐너 클라이언트 생성
                val scanner: BarcodeScanner = BarcodeScanning.getClient()

                // 바코드 스캔 시작
                scanner.process(image)
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
                    .addOnFailureListener { e ->
                        Log.e(tag, e.message ?: "Barcode scanning failed")
                    }
                    .addOnCompleteListener {
                        // 이미지 프로세싱 완료 후 이미지 닫기
                        imageProxy.close()
                    }
            }
        }

        // 카메라와 라이프사이클 바인딩
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
    }
}
