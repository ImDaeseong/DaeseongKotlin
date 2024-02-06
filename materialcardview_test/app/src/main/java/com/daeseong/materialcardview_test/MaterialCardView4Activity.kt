package com.daeseong.materialcardview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.card.MaterialCardView

class MaterialCardView4Activity : AppCompatActivity() {

    private lateinit var materialCardView: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_card_view4)
    }
}