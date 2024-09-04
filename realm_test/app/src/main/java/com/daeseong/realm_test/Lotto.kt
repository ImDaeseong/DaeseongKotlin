package com.daeseong.realm_test

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Lotto : RealmObject {
    @PrimaryKey
    var _rIndex: Int = 0

    var date: String? = null
    var part1: Int = 0
    var part2: Int = 0
    var part3: Int = 0
    var part4: Int = 0
    var part5: Int = 0
    var part6: Int = 0
    var bonus: Int = 0
}