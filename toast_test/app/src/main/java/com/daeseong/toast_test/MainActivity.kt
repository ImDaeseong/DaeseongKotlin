package com.daeseong.toast_test

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2:Button? = null
    private var button3: Button? = null
    private var button4:Button? = null
    private var button5:Button? = null
    private var ivbitmap: ImageView? = null

    private var toast : Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivbitmap = findViewById<ImageView>(R.id.ivbitmap)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            showToast(this, "Toast test")
        })

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener { view ->

            Snackbar.make(view, "Snackbar test", Snackbar.LENGTH_LONG).show()
        })

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(View.OnClickListener {

            val bitmap = getBitmapFromView(window.decorView)
            if (bitmap != null) {
                ivbitmap!!.setImageBitmap(bitmap)
            }
        })

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener(View.OnClickListener {

           Toast_layout1(this, "ClickToast ClickToast").show()
        })

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener(View.OnClickListener {

            Toast_layout2(this, "Toast test")
        })
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        try {
            val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(returnedBitmap)
            val bgDrawable = view.background
            if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
            view.draw(canvas)
            return returnedBitmap
        } catch (e: Exception) {
        }
        return null
    }

    private fun showToast(context: Context, sMsg: String) {

        val vlayout = View.inflate(context, R.layout.toast_layout, null)
        val tv1  = vlayout.findViewById<TextView>(R.id.tvtoast)
        tv1.text = sMsg;

        if(toast != null){
            toast!!.cancel()
        }

        toast = Toast(context)
        toast!!.view = vlayout
        toast!!.setGravity(Gravity.BOTTOM, 0, 0)
        toast!!.duration = Toast.LENGTH_LONG
        toast!!.show()
    }

}
