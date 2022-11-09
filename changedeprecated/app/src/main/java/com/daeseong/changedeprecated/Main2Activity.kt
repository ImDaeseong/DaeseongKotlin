package com.daeseong.changedeprecated

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.IOException
import kotlin.system.exitProcess

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var imageView1: ImageView? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null

    lateinit var  activityResultLauncherCamera: ActivityResultLauncher<Intent>
    lateinit var  activityResultLauncherGallery: ActivityResultLauncher<Intent>

    private var bitmap: Bitmap? = null
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initResultCamera()

        initResultGallery()

        imageView1 = findViewById(R.id.imageView1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            if (ActivityCompat.checkSelfPermission(this@Main2Activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this@Main2Activity, arrayOf(Manifest.permission.CAMERA),1)
            } else {

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                activityResultLauncherCamera.launch(intent)
            }
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            if (ActivityCompat.checkSelfPermission(this@Main2Activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this@Main2Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),2)
            } else {

                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                activityResultLauncherGallery!!.launch(intent)
            }
        })

        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener(View.OnClickListener {

            reStart()
        })
    }

    //앱 완전히 종료후 재시작
    private fun reStart() {
        val packageManager = packageManager
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = intent!!.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        exitProcess(0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                activityResultLauncherCamera!!.launch(intent)
            }

        } else if (requestCode == 2) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                activityResultLauncherGallery!!.launch(intent)
            }

        }
    }

    private fun initResultCamera() {

        //startActivityForResult 변경
        activityResultLauncherCamera = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.data != null && result.resultCode == RESULT_OK) {

                val bundle = result.data!!.extras
                bitmap = bundle!!["data"] as Bitmap?
                imageView1!!.setImageBitmap(bitmap)
            }
        }
    }

    private fun initResultGallery() {

        //startActivityForResult 변경
        activityResultLauncherGallery = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.data != null && result.resultCode == RESULT_OK) {

                uri = result.data!!.data

                try {

                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    imageView1!!.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}