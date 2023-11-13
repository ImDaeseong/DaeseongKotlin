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

    private val tag = MainActivity::class.java.simpleName

    private lateinit var dbHelperLotto: dbHelperLotto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelperLotto = dbHelperLotto()

        // 전체 데이터 삭제
        deleteLottoAll()

        // csv 파일에서 데이터를 가져와서 입력
        ReadCSVtoAdd()

        // 데이터 조회
        readLotto1()
        readLotto2()
        readLotto3()

        // 데이터 삭제
        deleteLotto()

        // 한개 데이터 입력
        addLotto()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelperLotto.closeRealm()
    }

    private fun readLotto1() {
        dbHelperLotto.lotto?.forEach {
            Log.e(
                tag, "${it?._rIndex} " +
                        "${it?.date} " +
                        "${it?.part1} " +
                        "${it?.part2} " +
                        "${it?.part3} " +
                        "${it?.part4} " +
                        "${it?.part5} " +
                        "${it?.part6} " +
                        "${it?.bonus}"
            )
        }
    }

    private fun readLotto2() {
        dbHelperLotto.lotto?.forEach { lotto ->
            Log.e(
                tag, "${lotto._rIndex} " +
                        "${lotto.date} " +
                        "${lotto.part1} " +
                        "${lotto.part2} " +
                        "${lotto.part3} " +
                        "${lotto.part4} " +
                        "${lotto.part5} " +
                        "${lotto.part6} " +
                        "${lotto.bonus}"
            )
        }
    }

    private fun readLotto3() {
        dbHelperLotto.getData(6)?.apply {
            Log.e(tag, _rIndex.toString())
            Log.e(tag, date!!)
            Log.e(tag, part1.toString())
            Log.e(tag, part2.toString())
            Log.e(tag, part3.toString())
            Log.e(tag, part4.toString())
            Log.e(tag, part5.toString())
            Log.e(tag, part6.toString())
            Log.e(tag, bonus.toString())
        }
    }

    private fun deleteLotto() {
        if (dbHelperLotto.isExistData(6)) {
            dbHelperLotto.deleteLotto(6)
        }
    }

    private fun deleteLottoAll() {
        val nTotalcount = dbHelperLotto.lottoRowCount
        Log.e(tag, "nTotalcount: $nTotalcount")
        dbHelperLotto.deleteLottoAll()
    }

    private fun addLotto() {
        dbHelperLotto.addLotto(6, "2022.03.25", 1, 1, 1, 1, 1, 1, 64)
    }

    private fun ReadCSVtoAdd() {
        try {
            CSVReader(
                InputStreamReader(
                    resources.assets.open("lotto.csv"),
                    "UTF-8"
                )
            ).use { csvReader ->
                dbHelperLotto.getRealm().beginTransaction()
                val result = csvReader.readAll()
                result.forEachIndexed { _, data ->

                    val nMax = dbHelperLotto.getRealm().where(Lotto::class.java).max("_rIndex")
                    val nextId = nMax?.toInt() ?: 1
                    val lotto = dbHelperLotto.getRealm().createObject<Lotto>(nextId)
                    lotto.date = data.getOrNull(1)
                    lotto.part1 = data.getOrNull(2)?.toInt() ?: 0
                    lotto.part2 = data.getOrNull(3)?.toInt() ?: 0
                    lotto.part3 = data.getOrNull(4)?.toInt() ?: 0
                    lotto.part4 = data.getOrNull(5)?.toInt() ?: 0
                    lotto.part5 = data.getOrNull(6)?.toInt() ?: 0
                    lotto.part6 = data.getOrNull(7)?.toInt() ?: 0
                    lotto.bonus = data.getOrNull(8)?.toInt() ?: 0
                }
                dbHelperLotto.getRealm().commitTransaction()
            }

        } catch (ex: FileNotFoundException) {
            ex.printStackTrace()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}
