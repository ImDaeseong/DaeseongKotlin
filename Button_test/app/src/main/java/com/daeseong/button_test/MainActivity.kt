package com.daeseong.button_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() , View.OnClickListener {

    private val TAG: String = MainActivity::class.java.simpleName;

    private var button1 : Button? = null;
    private var button2 : Button? = null;
    private var button3 : Button? = null;
    private var button4 : Button? = null;
    private var button5 : Button? = null;
    private var button6 : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(this);

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener(this);

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(this);

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener(this);

        button5 = findViewById<Button>(R.id.button5)
        button5!!.setOnClickListener(this);

        button6 = findViewById<Button>(R.id.button6)
        button6!!.setOnClickListener(this);
    }

    override fun onClick(v: View?) {

        if (v != null) {

            when (v.id) {

                R.id.button1 -> {

                    //Log.e(TAG, "button1_Click")

                    val intent = Intent(this, Button1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button2 -> {

                    //Log.e(TAG, "button2_Click")

                    val intent = Intent(this, Button2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button3 -> {

                    Log.e(TAG, "button3_Click")

                    val intent = Intent(this, Button3Activity::class.java)
                    startActivity(intent)
                }

                R.id.button4 -> {

                    //Log.e(TAG, "button4_Click")

                    val intent = Intent(this, Button4Activity::class.java)
                    startActivity(intent)
                }

                R.id.button5 -> {

                    //Log.e(TAG, "button5_Click")

                    val intent = Intent(this, Button5Activity::class.java)
                    startActivity(intent)
                }

                R.id.button6 -> {

                    //Log.e(TAG, "button6_Click")

                    val intent = Intent(this, Button6Activity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}
