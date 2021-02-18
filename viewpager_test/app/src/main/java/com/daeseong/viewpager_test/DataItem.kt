package com.daeseong.viewpager_test

class DataItem {

    private  var title: String = ""
    private var imgID : Int = 0
    private var bgColor : Int = 0

    constructor() {}

    constructor(title: String, imgID: Int, bgColor: Int) {
        this.title = title
        this.imgID = imgID
        this.bgColor = bgColor
    }

    fun getTitle(): String {
        return this.title
    }

    fun getImgID(): Int {
        return this.imgID
    }

    fun getBgColor(): Int {
        return this.bgColor
    }
}