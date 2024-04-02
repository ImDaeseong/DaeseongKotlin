package com.daeseong.toast_test

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button

    private lateinit var ivbitmap: ImageView
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivbitmap = findViewById(R.id.ivbitmap)

        button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            showToast("Toast test")
        }

        button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener { view ->
            Snackbar.make(view, "Snackbar test", Snackbar.LENGTH_LONG).show()
        }

        button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            val bitmap = getBitmapFromView(window.decorView)
            ivbitmap.setImageBitmap(bitmap)
        }

        button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            showCustomToast("ClickToast ClickToast", Gravity.CENTER, Toast.LENGTH_SHORT)
        }

        button5 = findViewById<Button>(R.id.button5)
        button5.setOnClickListener {
            showToast("Toast test")
        }

        button6 = findViewById<Button>(R.id.button6)
        button6.setOnClickListener {
            val toastLayout = ToastLayout3(this, "Toast test", Toast.LENGTH_SHORT)
            toastLayout.show()
        }

        button7 = findViewById<Button>(R.id.button7)
        button7.setOnClickListener {
            val rootView = findViewById<View>(android.R.id.content)
            val Snackbar = CustomSnackbar(this, rootView, "여기에 메시지를 입력하세요.")
        }

        button8 = findViewById<Button>(R.id.button8)
        button8.setOnClickListener {

            val rootView = findViewById<View>(android.R.id.content)
            val snackbar = Snackbar.make(rootView, "여기에 메시지를 입력하세요.", Snackbar.LENGTH_LONG)

            //배경색
            snackbar.view.setBackgroundColor(Color.WHITE)

            //글자색,가운데정렬
            val textView = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.BLACK)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            }

            snackbar.show()
        }
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        return try {
            val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(returnedBitmap)
            val bgDrawable = view.background
            if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
            view.draw(canvas)
            returnedBitmap
        } catch (e: Exception) {
            null
        }
    }

    private fun showToast(message: String) {
        val toastLayout = layoutInflater.inflate(R.layout.toast_layout, null)
        val toastTextView = toastLayout.findViewById<TextView>(R.id.tvtoast)
        toastTextView.text = message

        if (::toast.isInitialized) {
            toast.cancel()
        }

        toast = Toast(applicationContext)
        toast.view = toastLayout
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.show()
    }

    private fun showCustomToast(message: String, gravity: Int, duration: Int) {
        val toastLayout = layoutInflater.inflate(R.layout.toast_layout, null)
        val toastTextView = toastLayout.findViewById<TextView>(R.id.tvtoast)
        toastTextView.text = message

        if (::toast.isInitialized) {
            toast.cancel()
        }

        toast = Toast(applicationContext)
        toast.view = toastLayout
        toast.setGravity(gravity, 0, 0)
        toast.duration = duration
        toast.show()
    }

}
