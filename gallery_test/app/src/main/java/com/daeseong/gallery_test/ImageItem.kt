package com.daeseong.gallery_test

import android.graphics.Bitmap

class ImageItem {

    private var bitmap: Bitmap? = null
    private var title: String = ""

    constructor() {}

    constructor(bitmap: Bitmap, title: String) {
        this.bitmap = bitmap
        this.title = title
    }

    fun getBitmap(): Bitmap {
        return this.bitmap!!
    }

    fun getTitle(): String {
        return this.title
    }

    fun setBitmap(bitmap: Bitmap) {
        this.bitmap = bitmap
    }

    fun setTitle(title: String) {
        this.title = title
    }
}

