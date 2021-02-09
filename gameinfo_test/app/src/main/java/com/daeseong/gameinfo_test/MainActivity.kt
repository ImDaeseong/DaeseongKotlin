package com.daeseong.gameinfo_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.gameinfo_test.GameInfo.GameItem


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var btn1 : Button? = null;
    private var btn2 : Button? = null;
    private var btn3 : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener {

            Handler().postDelayed(Runnable {

                val gameItems: List<GameItem> = GameInfo().getGameApp(this)

                for (i in gameItems.indices) {

                    Log.e(tag, "packageName:" + gameItems[i].packageName)
                }
            }, 100)
        }

        btn2 = findViewById<Button>(R.id.btn2)
        btn2!!.setOnClickListener {

            startService(Intent(this, GameCheckService::class.java))
        }

        btn3 = findViewById<Button>(R.id.btn3)
        btn3!!.setOnClickListener {

            stopService(Intent(this, GameCheckService::class.java))
        }
    }
}
