package com.im.daeseong.singleton_test.Db

data class Alarm(var id: Int = 0, var title: String? = null, var content: String? = null, var writeDate: String? = null) {
    constructor(title: String, content: String) : this(0, title, content, null)
}
