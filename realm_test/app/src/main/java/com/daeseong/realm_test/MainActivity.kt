package com.daeseong.realm_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.opencsv.CSVReader
import io.realm.kotlin.createObject
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private var dbHelperLotto: dbHelperLotto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelperLotto = dbHelperLotto()

        //전체 데이터 삭제
        //deleteLottoAll()

        //csv 파일에서 데이터를 가져와서 입력
        //ReadCSVtoAdd()

        //데이터 조회
        readLotto1()
        //readLotto2()
        //readLotto3()

        //데이터 삭제
        //deleteLotto()

        //한개 데이터 입력
        //addLotto()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelperLotto!!.closeRealm()
    }

    private fun readLotto1() {
        val realmResults = dbHelperLotto!!.lotto
        for (i in realmResults!!.indices) {
            Log.e(
                TAG, realmResults[i]!!._rIndex.toString() + " "
                        + realmResults[i]!!.date + " "
                        + realmResults[i]!!.part1 + " "
                        + realmResults[i]!!.part2 + " "
                        + realmResults[i]!!.part3 + " "
                        + realmResults[i]!!.part4 + " "
                        + realmResults[i]!!.part5 + " "
                        + realmResults[i]!!.part6 + " "
                        + realmResults[i]!!.bonus
            )
        }
    }

    private fun readLotto2() {
        val realmResults = dbHelperLotto!!.lotto
        for (lotto in realmResults!!) {
            Log.e(
                TAG, lotto._rIndex.toString() + " "
                        + lotto.date + " "
                        + lotto.part1 + " "
                        + lotto.part2 + " "
                        + lotto.part3 + " "
                        + lotto.part4 + " "
                        + lotto.part5 + " "
                        + lotto.part6 + " "
                        + lotto.bonus
            )
        }
    }

    private fun readLotto3() {
        val lotto = dbHelperLotto!!.getData(6)
        if (lotto != null) {
            Log.e(TAG, java.lang.String.valueOf(lotto._rIndex))
            Log.e(TAG, lotto.date!!)
            Log.e(TAG, java.lang.String.valueOf(lotto.part1))
            Log.e(TAG, java.lang.String.valueOf(lotto.part2))
            Log.e(TAG, java.lang.String.valueOf(lotto.part3))
            Log.e(TAG, java.lang.String.valueOf(lotto.part4))
            Log.e(TAG, java.lang.String.valueOf(lotto.part5))
            Log.e(TAG, java.lang.String.valueOf(lotto.part6))
            Log.e(TAG, java.lang.String.valueOf(lotto.bonus))
        }
    }

    private fun deleteLotto() {
        val bfind = dbHelperLotto!!.isExistData(6)
        if (bfind) {
            dbHelperLotto!!.deleteLotto(6)
        }
    }

    private fun deleteLottoAll() {
        val nTotalcount = dbHelperLotto!!.lottoRowCount
        Log.e(TAG, "nTotalcount: $nTotalcount")
        dbHelperLotto!!.deleteLottoAll()
    }

    private fun addLotto() {
        dbHelperLotto!!.addLotto(6, "2022.03.25", 1, 1, 1, 1, 1, 1, 64)
    }

    private fun ReadCSVtoAdd() {
        try {

            var csvReader = CSVReader(
                InputStreamReader(
                    resources.assets.open("lotto.csv"),
                    "UTF-8"
                )
            )

            dbHelperLotto!!.getRealm().beginTransaction()

            val result = csvReader.readAll()
            result.forEachIndexed { index, data ->

                val nMax = dbHelperLotto!!.getRealm().where(Lotto::class.java).max("_rIndex")
                val nextId = if (nMax == null) 1 else nMax.toInt() + 1
                val lotto = dbHelperLotto!!.getRealm().createObject<Lotto>(nextId)
                lotto.date = data[1]
                lotto.part1 =data[2].toInt()
                lotto.part2 = data[3].toInt()
                lotto.part3 = data[4].toInt()
                lotto.part4 = data[5].toInt()
                lotto.part5 = data[6].toInt()
                lotto.part6 = data[7].toInt()
                lotto.bonus = data[8].toInt()
            }

            dbHelperLotto!!.getRealm().commitTransaction()

        } catch (ex: FileNotFoundException) {
            ex.printStackTrace()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}