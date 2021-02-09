package com.daeseong.alertdialog_test

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)
            var viewlayout = layoutInflater.inflate(R.layout.alert_message, null)
            builder.setTitle("제목")
            builder.setMessage("공지사항 내용표현\n공지사항 내용표현")
                .setCancelable(false)
                .setPositiveButton("종료") { dialog, id ->

                    Log.e(tag, "프로그램을 종료")
                    //MainActivity.this.finish();
                }
                .setNegativeButton("취소") { dialog, id ->

                    Log.e(tag, "다이얼로그를 취소")
                    //dialog.cancel();
                }

            builder.setView(viewlayout)
            val alertDialog = builder.create()
            alertDialog.show()

            val btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            btnNegative.setBackgroundColor(Color.MAGENTA);
            btnNegative.setTextColor(Color.BLACK)

            val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            btnPositive.setBackgroundColor(Color.YELLOW);
            btnPositive.setTextColor(Color.BLACK)

        })

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)
            builder.setTitle("title")
            builder.setMessage("공지사항 내용표현\n공지사항 내용표현")
                .setCancelable(false)
                .setPositiveButton("종료") { dialog, id ->

                    Log.e(tag, "프로그램을 종료")
                    //MainActivity.this.finish();
                }
                .setNegativeButton("취소") { dialog, id ->

                    Log.e(tag, "다이얼로그를 취소")
                    //dialog.cancel();
                }

            val alertDialog = builder.create()
            alertDialog.show()

            val btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            //btnNegative.setBackgroundColor(Color.MAGENTA);
            btnNegative.setTextColor(Color.BLACK)

            val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            //btnPositive.setBackgroundColor(Color.YELLOW);
            btnPositive.setTextColor(Color.BLACK)

        })

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(View.OnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)
            builder.setTitle("title")
            builder.setMessage("공지사항 내용표현\n공지사항 내용표현")
                .setCancelable(false)
                .setPositiveButton("종료") { dialog, id ->

                    Log.e(tag, "프로그램을 종료")
                    //MainActivity.this.finish();
                }

            val alertDialog = builder.create()
            alertDialog.show()

            val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            //btnPositive.setBackgroundColor(Color.YELLOW);
            btnPositive.setTextColor(Color.BLACK)
        })

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener(View.OnClickListener {

            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme1)
            builder.setTitle(Html.fromHtml("<font color='#FF7F27'>제목</font>"))
            builder.setMessage(Html.fromHtml("<font color='#FF7F27'>공지사항 내용표현<br>공지사항 내용표현</font>"))
                .setCancelable(false)
                .setPositiveButton("종료") { dialog, id ->

                    Log.e(tag, "프로그램을 종료")
                    //MainActivity.this.finish();
                }
                .setNegativeButton("취소") { dialog, id ->

                    Log.e(tag, "다이얼로그를 취소")
                    //dialog.cancel();
                }

            val alertDialog = builder.create()
            alertDialog.show()

            val btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            //btnNegative.setBackgroundColor(Color.MAGENTA);
            btnNegative.setTextColor(Color.BLACK)

            val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            //btnPositive.setBackgroundColor(Color.YELLOW);
            btnPositive.setTextColor(Color.BLACK)
        })


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
