package com.daeseong.cardview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView

class CardView3Activity : AppCompatActivity() {

    private val tag: String = CardView3Activity::class.java.simpleName

    private lateinit var cardview1: CardView
    private lateinit var cardview2: CardView
    private lateinit var cardview3: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view3)

        cardview1 = findViewById(R.id.cardview1)
        cardview1.setOnClickListener {
            Log.e(tag, "CardView1 setOnClickListener")
        }

        cardview2 = findViewById(R.id.cardview2)
        cardview2.setOnClickListener {
            Log.e(tag, "CardView2 setOnClickListener")
        }

        cardview3 = findViewById(R.id.cardview3)
        cardview3.setOnClickListener {
            Log.e(tag, "CardView3 setOnClickListener")
        }
    }
}
