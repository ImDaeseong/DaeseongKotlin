package com.daeseong.alertdialog_test

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)
            val viewLayout = layoutInflater.inflate(R.layout.alert_message, null)

            builder.setTitle("제목")
                .setMessage("공지사항 내용표현\n공지사항 내용표현")
                .setCancelable(false)
                .setPositiveButton("종료") { _, _ ->
                    Log.e(tag, "프로그램을 종료")
                }
                .setNegativeButton("취소") { _, _ ->
                    Log.e(tag, "다이얼로그를 취소")
                }

            builder.setView(viewLayout)
            val alertDialog = builder.create()
            alertDialog.show()

            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.apply {
                setBackgroundColor(Color.MAGENTA)
                setTextColor(Color.BLACK)
            }

            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
                setBackgroundColor(Color.YELLOW)
                setTextColor(Color.BLACK)
            }
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)

            builder.setTitle("title")
                .setMessage("공지사항 내용표현\n공지사항 내용표현")
                .setCancelable(false)
                .setPositiveButton("종료") { _, _ ->
                    Log.e(tag, "프로그램을 종료")
                }
                .setNegativeButton("취소") { _, _ ->
                    Log.e(tag, "다이얼로그를 취소")
                }

            val alertDialog = builder.create()
            alertDialog.show()

            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.apply {
                setTextColor(Color.BLACK)
            }

            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
                setTextColor(Color.BLACK)
            }
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)

            builder.setTitle("title")
                .setMessage("공지사항 내용표현\n공지사항 내용표현")
                .setCancelable(false)
                .setPositiveButton("종료") { _, _ ->
                    Log.e(tag, "프로그램을 종료")
                }

            val alertDialog = builder.create()
            alertDialog.show()

            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
                setTextColor(Color.BLACK)
            }
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)

            builder.setTitle(Html.fromHtml("<font color='#FF7F27'>제목</font>"))
                .setMessage(Html.fromHtml("<font color='#FF7F27'>공지사항 내용표현<br>공지사항 내용표현</font>"))
                .setCancelable(false)
                .setPositiveButton("종료") { _, _ ->
                    Log.e(tag, "프로그램을 종료")
                }
                .setNegativeButton("취소") { _, _ ->
                    Log.e(tag, "다이얼로그를 취소")
                }

            val alertDialog = builder.create()
            alertDialog.show()

            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.apply {
                setTextColor(Color.BLACK)
            }

            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
                setTextColor(Color.BLACK)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
