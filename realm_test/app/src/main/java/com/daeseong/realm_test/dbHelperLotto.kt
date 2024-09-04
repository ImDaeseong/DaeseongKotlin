package com.daeseong.realm_test

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class dbHelperLotto {

    private val TAG = dbHelperLotto::class.java.simpleName
    private var realm: Realm

    init {
        val config = RealmConfiguration.create(schema = setOf(Lotto::class))
        realm = Realm.open(config)
    }

    val lotto: Flow<List<Lotto>>
        get() = realm.query<Lotto>().sort("_rIndex", Sort.DESCENDING).asFlow().map { it.list }

    fun closeRealm() {
        try {
            realm.close()
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }

    suspend fun addLotto(rIndex: Int, Date: String?, Part1: Int, Part2: Int, Part3: Int, Part4: Int, Part5: Int, Part6: Int, Bonus: Int) {
        try {
            realm.write {
                copyToRealm(Lotto().apply {
                    this._rIndex = rIndex
                    this.date = Date
                    this.part1 = Part1
                    this.part2 = Part2
                    this.part3 = Part3
                    this.part4 = Part4
                    this.part5 = Part5
                    this.part6 = Part6
                    this.bonus = Bonus
                })
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }

    fun isExistData(rIndex: Int): Boolean {
        return try {
            realm.query<Lotto>("_rIndex == $0", rIndex).first().find() != null
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
            false
        }
    }

    fun getData(rIndex: Int): Lotto? {
        return try {
            realm.query<Lotto>("_rIndex == $0", rIndex).first().find()
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
            null
        }
    }

    suspend fun deleteLotto(rIndex: Int) {
        try {
            realm.write {
                val lotto = query<Lotto>("_rIndex == $0", rIndex).first().find()
                lotto?.let { delete(it) }
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }

    val lottoRowCount: Long
        get() {
            return try {
                realm.query<Lotto>().count().find()
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
                0
            }
        }

    suspend fun deleteLottoAll() {
        try {
            realm.write {
                val lottos = query<Lotto>().find()
                delete(lottos)
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }
}