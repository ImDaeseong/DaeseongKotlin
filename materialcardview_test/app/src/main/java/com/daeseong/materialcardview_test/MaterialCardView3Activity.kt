package com.daeseong.materialcardview_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

class MaterialCardView3Activity : AppCompatActivity() {

    private lateinit var cv2: MaterialCardView
    private lateinit var cv3: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_card_view3)

        init()
    }

    private fun init() {
        cv2 = findViewById(R.id.cv2)
        cv2.background = ContextCompat.getDrawable(this, R.drawable.topround)

        cv3 = findViewById(R.id.cv3)
        cv3.background = ContextCompat.getDrawable(this, R.drawable.bottomround)
    }
}