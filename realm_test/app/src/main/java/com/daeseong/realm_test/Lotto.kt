package com.daeseong.realm_test

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Lotto : RealmObject() {
    @PrimaryKey
    var _rIndex = 0

    var date: String? = null
    var part1 = 0
    var part2 = 0
    var part3 = 0
    var part4 = 0
    var part5 = 0
    var part6 = 0
    var bonus = 0
}
