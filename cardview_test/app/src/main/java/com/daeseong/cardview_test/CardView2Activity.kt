package com.daeseong.cardview_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class CardView2Activity : AppCompatActivity() {

    private val tag: String = CardView2Activity::class.java.simpleName

    private var cardView: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view2)

        cardView = findViewById<CardView>(R.id.cardview2)
        cardView!!.setOnClickListener {
            Log.e(tag, "CardView setOnClickListener")
        }
    }
}
