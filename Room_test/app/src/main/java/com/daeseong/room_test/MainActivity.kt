package com.daeseong.room_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnRead: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var textResult: TextView

    private lateinit var alarmDao: AlarmDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTitle = findViewById(R.id.editTitle)
        editContent = findViewById(R.id.editContent)
        btnAdd = findViewById(R.id.btnAdd)
        btnRead = findViewById(R.id.btnRead)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        textResult = findViewById(R.id.textResult)

        btnAdd.setOnClickListener(this)
        btnRead.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)

        alarmDao = AppDatabase.getInstance(this).alarmDao()

        // 전체 개수 조회
        updateRowCount()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAdd -> add()
            R.id.btnRead -> read()
            R.id.btnUpdate -> update()
            R.id.btnDelete -> delete()
        }
    }

    private fun add() {
        getCompletable {
            val title = editTitle.text.toString()
            val content = editContent.text.toString()

            val alarm = Alarm(title = title, content = content, regDate = Date())
            alarmDao.insert(alarm)

            // 입력 데이터가 10개가 넘으면 가장 오래된 데이터 삭제
            val rowCount = alarmDao.getRowCount()
            if (rowCount > 10) {
                alarmDao.deleteOldest()
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onComplete, ::onError)
    }

    private fun read() {
        getObservable {
            alarmDao.getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onReadSuccess, ::onError)
    }

    private fun update() {
        getCompletable {
            val title = editTitle.text.toString()
            val content = editContent.text.toString()

            if (alarmDao.isAlarmExists(title)) {
                val alarm = alarmDao.getAlarmByTitle(title)
                alarm?.let {
                    it.content = content
                    alarmDao.update(it)
                }
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onComplete, ::onError)
    }

    private fun delete() {
        getCompletable {
            val title = editTitle.text.toString()
            val alarm = alarmDao.getAlarmByTitle(title)
            alarm?.let { alarmDao.delete(it) }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onComplete, ::onError)
    }

    private fun updateRowCount() {
        getObservable {
            alarmDao.getRowCount()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onRowCount, ::onError)
    }

    private fun getCompletable(runnable: () -> Unit): Completable {
        return Completable.fromRunnable {
            try {
                runnable.invoke()
            } catch (e: Exception) {
                Log.e(tag, e.message.orEmpty())
            }
        }
    }

    private fun <T : Any> getObservable(callable: () -> T): Observable<T> {
        return Observable.fromCallable {
            try {
                callable.invoke()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

    private fun onComplete() {
        editTitle.setText("")
        editContent.setText("")
        editTitle.requestFocus()

        // 전체 개수 조회
        updateRowCount()
    }

    private fun onReadSuccess(alarmList: List<Alarm>) {
        val sMsg = StringBuilder()
        for (alarm in alarmList) {
            val line = String.format("id:%d title:%s content:%s regDate:%s%n",
                alarm.id, alarm.title, alarm.content, formatDate(alarm.regDate))
            sMsg.append(line)
        }
        textResult.text = sMsg.toString()
    }

    private fun onError(throwable: Throwable) {
        Log.e(tag, throwable.message.orEmpty())
    }

    private fun onRowCount(rowCount: Int) {
        val sCount = "개수:$rowCount"
        textResult.text = sCount
    }

    private fun formatDate(date: Date?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(date)
    }
}
