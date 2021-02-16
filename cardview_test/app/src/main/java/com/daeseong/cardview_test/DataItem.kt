package com.daeseong.cardview_test

class DataItem {

    @JvmName("getTitleString")
    fun getTitle(): String {
        return title!!
    }

    @JvmName("getImageInt")
    fun getImage(): Int {
        return image!!
    }

    var image: Int? = null
    var title: String? = null

    constructor()

    constructor(image: Int, title: String){
        this.image = image
        this.title = title
    }
}