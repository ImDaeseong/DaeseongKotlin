package com.daeseong.changedeprecated

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.changedeprecated.Common.GetBitmapTask
import com.daeseong.changedeprecated.Common.GetStringTask
import java.util.concurrent.Future

class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private lateinit var imageView1: ImageView
    private lateinit var textView1: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button

    private val sPngUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"
    private val sStrUrl = "https://api.bithumb.com/public/ticker/BTC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        imageView1 = findViewById(R.id.imageView1)
        textView1 = findViewById(R.id.textView1)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        button1.setOnClickListener {

            try {

                val task = GetBitmapTask()
                val future: Future<Bitmap?> = task.execute(sPngUrl)
                val bitmap: Bitmap? = future.get()
                if (bitmap != null) {
                    imageView1.setImageBitmap(bitmap)
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        button2.setOnClickListener {

            try {

                val task = GetStringTask()
                val future: Future<String?> = task.execute(sStrUrl)
                val result: String? = future.get()
                if (result != null) {
                    textView1.text = result
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

}
