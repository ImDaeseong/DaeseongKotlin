package com.daeseong.rxjava3_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName
    private val DATABASE_NAME = "IndonesiaDB.db"
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        if (!isFile()) {
            copyDatabaseFile(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun copyDatabaseFile(context: Context) {

        val databasePath = "/data/data/${context.packageName}/databases/"
        val databaseFile = File(databasePath, DATABASE_NAME)

        disposable = Observable.fromCallable {
            try {

                if (!databaseFile.exists()) {

                    val dir = File(databasePath)
                    if (!dir.exists()) {
                        dir.mkdirs()
                    }

                    context.assets.open(DATABASE_NAME).use { inputStream ->
                        FileOutputStream(databaseFile).use { outputStream ->
                            val buffer = ByteArray(1024)
                            var length: Int
                            while (inputStream.read(buffer).also { length = it } > 0) {
                                outputStream.write(buffer, 0, length)
                            }
                            outputStream.flush()
                        }
                    }
                }
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    if (success) {
                        Log.e(tag, "success")
                    }
                },
                { throwable ->
                    Log.e(tag, "error")
                    throwable.printStackTrace()
                }
            )
    }

    private fun isFile(): Boolean {
        val filePath = "/data/data/$packageName/databases/$DATABASE_NAME"
        return File(filePath).exists()
    }
}
