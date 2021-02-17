package com.daeseong.db_test.SQLiteOpenHelper

class Alarm {

    private var id : Int = 0
    private var title: String = ""
    private var content: String = ""
    private var writeDate: String = ""

    constructor() {}

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

    fun getId(): Int {
        return this.id
    }

    fun getTitle(): String {
        return this.title
    }

    fun getContent(): String {
        return this.content
    }

    fun getWriteDate(): String {
        return this.writeDate
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setContent(content: String) {
        this.content = content
    }

    fun setWriteDate(writeDate: String) {
        this.writeDate = writeDate
    }
}