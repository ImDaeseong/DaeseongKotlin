package com.daeseong.db_test.SQLiteOpenHelper

class Alarm {
    var id: Int = 0
    var title: String = ""
    var content: String = ""
    var writeDate: String = ""

    constructor()

    constructor(title: String, content: String) {
        this.title = title
        this.content = content
    }

    constructor(id: Int, title: String, content: String, writeDate: String) {
        this.id = id
        this.title = title
        this.content = content
        this.writeDate = writeDate
    }
}