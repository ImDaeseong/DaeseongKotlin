package com.daeseong.admob_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() , View.OnClickListener{

    private val tag: String = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener(this)

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener(this)

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener(this)

        button4 = findViewById<Button>(R.id.button4)
        button4!!.setOnClickListener(this)


        //광고 초기화
        MobileAds.initialize(this) {

        }
    }

    override fun onClick(v: View?) {

        if (v != null) {

            when (v.id) {

                R.id.button1 -> {

                    val intent = Intent(this, AdView1Activity::class.java)
                    startActivity(intent)
                }

                R.id.button2 -> {

                    val intent = Intent(this, AdView2Activity::class.java)
                    startActivity(intent)
                }

                R.id.button3 -> {

                    val intent = Intent(this, AdView3Activity::class.java)
                    startActivity(intent)
                }

                R.id.button4 -> {

                    val intent = Intent(this, AdView4Activity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}