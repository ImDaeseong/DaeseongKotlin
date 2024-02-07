package com.daeseong.cardview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class CardView7Activity : AppCompatActivity() {

    private lateinit var cv2: CardView
    private lateinit var cv3: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view7)

        init()
    }

    private fun init() {
        cv2 = findViewById(R.id.cv2)
        cv2.background = ContextCompat.getDrawable(this, R.drawable.topround)

        cv3 = findViewById(R.id.cv3)
        cv3.background = ContextCompat.getDrawable(this, R.drawable.bottomround)
    }
}