package com.daeseong.alarm_test

class Alarm {

    @JvmName("getID")
    fun getID(): Int {
        return this.ID
    }

    @JvmName("getHour")
    fun getHour(): Int {
        return this.nHour
    }

    @JvmName("getMinute")
    fun getMinute(): Int {
        return this.nMinute
    }

    fun setID(ID: Int) {
        this.ID = ID
    }

    fun setHour(nHour: Int) {
        this.nHour = nHour
    }

    fun setMinute(nMinute: Int) {
        this.nMinute = nMinute
    }

    private var ID : Int = 0
    private var nHour : Int = 0
    private var nMinute : Int = 0

    constructor() {}

    constructor(id: Int, nHour: Int, nMinute: Int) {
        this.ID = id
        this.nHour = nHour
        this.nMinute = nMinute
    }
}