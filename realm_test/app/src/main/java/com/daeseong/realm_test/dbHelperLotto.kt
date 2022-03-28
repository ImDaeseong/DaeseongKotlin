package com.daeseong.realm_test

import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class dbHelperLotto {

    private val TAG = dbHelperLotto::class.java.simpleName

    companion object {
        private var realm: Realm? = null
        private var realmResults: RealmResults<Lotto>? = null
    }

    init {
        realm = MyApplicaton.getInstance().getRealm()
    }

    val lotto: RealmResults<Lotto>?
        get() {
            try {
                return realm!!.where(Lotto::class.java) //.sort("_rIndex", Sort.ASCENDING)
                    .sort("_rIndex", Sort.DESCENDING)
                    .findAll().also { realmResults = it }
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
            return null
        }

    fun getRealm(): Realm {
        return realm!!
    }

    fun closeRealm() {
        try {
            if (realm != null) {
                realm!!.close()
                realm = null
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }

    fun addLotto(rIndex: Int, Date: String?, Part1: Int, Part2: Int, Part3: Int, Part4: Int, Part5: Int, Part6: Int, Bonus: Int) {
        realm!!.executeTransaction { realm ->
            try {
                val lotto = realm.createObject(Lotto::class.java, rIndex)
                lotto.date = Date
                lotto.part1 = Part1
                lotto.part2 = Part2
                lotto.part3 = Part3
                lotto.part4 = Part4
                lotto.part5 = Part5
                lotto.part6 = Part6
                lotto.bonus = Bonus
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun isExistData(rIndex: Int): Boolean {
        var bFindData = false
        try {
            val lotto = realm!!.where(Lotto::class.java).equalTo("_rIndex", rIndex).findFirst()
            if (lotto != null) bFindData = true
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
        return bFindData
    }

    fun getData(rIndex: Int): Lotto? {
        try {
            return realm!!.where(Lotto::class.java).equalTo("_rIndex", rIndex).findFirst()
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
        return null
    }

    fun deleteLotto(rIndex: Int) {
        realm!!.executeTransaction { realm ->
            try {
                val lotto =
                    realm.where(Lotto::class.java).equalTo("_rIndex", rIndex).findFirst()
                lotto?.deleteFromRealm()
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    val lottoRowCount: Int
        get() {
            var nTotalcount = 0
            try {
                val results = realm!!.where(Lotto::class.java).findAll()
                nTotalcount = results.size
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
            return nTotalcount
        }

    fun deleteLottoAll() {
        try {
            realm!!.beginTransaction()
            realm!!.deleteAll()
            realm!!.commitTransaction()
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }
}
