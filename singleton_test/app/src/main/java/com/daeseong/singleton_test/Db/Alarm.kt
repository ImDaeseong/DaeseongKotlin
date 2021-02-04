package com.daeseong.singleton_test.Db

/*
data class Alarm (
    var id : Int,
    var title: String,
    var content: String,
    var writeDate: String
)
*/

class Alarm {
    var id : Int
    var title: String
    var content: String
    var writeDate: String

    init {
        id = 0
        title = ""
        content = ""
        writeDate = ""
    }

    constructor() {
    }

    constructor(title: String, content: String) {
        this.title = title
        this.content = content
    }

    constructor(id: Int, title: String, content: String,  writeDate: String) {
        this.id = id
        this.title = title
        this.content = content
        this.writeDate = writeDate
    }
}