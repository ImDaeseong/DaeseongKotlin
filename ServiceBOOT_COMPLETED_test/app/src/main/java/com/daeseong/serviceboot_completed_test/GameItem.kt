package com.daeseong.serviceboot_completed_test

import android.graphics.drawable.Drawable

data class GameItem (
    var appName: String,
    var packageName : String,
    var versionName : String,
    var versoinCode : Int,
    var gameIcon : Drawable
)