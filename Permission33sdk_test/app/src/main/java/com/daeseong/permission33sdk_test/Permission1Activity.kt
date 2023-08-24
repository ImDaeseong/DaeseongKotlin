package com.daeseong.permission33sdk_test

import android.Manifest.permission.*
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permission1Activity : AppCompatActivity() {

    private val TAG = Permission1Activity::class.simpleName

    private lateinit var imageView1: ImageView
    private lateinit var button1: Button
    private lateinit var button2: Button

    private var bitmap: Bitmap? = null
    private var uri: Uri? = null

    private lateinit var activityResultLauncherGallery: ActivityResultLauncher<Intent>
    private lateinit var activityResultLauncherCamera: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission1)

        initResultGallery()
        initResultCamera()

        imageView1 = findViewById(R.id.imageView1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                if (ContextCompat.checkSelfPermission(this, READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(READ_MEDIA_IMAGES),1)
                } else {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    activityResultLauncherGallery.launch(intent)
                }

            } else {

                if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE),1)
                } else {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    activityResultLauncherGallery.launch(intent)
                }
            }
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(CAMERA), 2)
            } else {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                activityResultLauncherCamera.launch(intent)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                activityResultLauncherGallery.launch(intent)
            }

        } else if (requestCode == 2) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                activityResultLauncherCamera.launch(intent)
            }
        }
    }

    private fun initResultGallery() {
        activityResultLauncherGallery =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.data != null && result.resultCode == RESULT_OK) {

                    uri = result.data!!.data

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                        imageView1.setImageBitmap(bitmap)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }

    private fun initResultCamera() {
        activityResultLauncherCamera =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.data != null && result.resultCode == RESULT_OK) {
                    val bundle = result.data!!.extras
                    bitmap = bundle?.get("data") as Bitmap?
                    imageView1.setImageBitmap(bitmap)
                }
            }
    }
}