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
import java.io.OutputStream

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private val DATABASE_NAME = "IndonesiaDB.db"

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        try {
            val bFile: Boolean = isFile()
            if (!bFile) {

                //Log.e(tag, "isFile Not Found")
                CopyDBfile(this)
            } else {

                //Log.e(tag, "isFile Found")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun CopyDBfile(context: Context) {

        val PATH_DATABASE = "/data/data/" + context.applicationContext.packageName + "/databases/"

        disposable = Observable.fromCallable {

            //Log.e(tag, "doInBackground")

            try {

                val myinput = context.assets.open(DATABASE_NAME)
                val file = File(PATH_DATABASE + DATABASE_NAME)

                if (!file.exists()) {
                    val dir = File(PATH_DATABASE)
                    dir.mkdirs()

                    val myoutput: OutputStream = FileOutputStream(PATH_DATABASE + DATABASE_NAME)
                    val buffer = ByteArray(1024)
                    var length: Int
                    while (myinput.read(buffer).also { length = it } > 0) {
                        myoutput.write(buffer, 0, length)
                    }

                    myoutput.flush()
                    myoutput.close()
                    myinput.close()
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }

            false
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { result: Boolean? ->

            //Log.e(tag, "onPostExecute")
            disposable!!.dispose()
        }
    }

    private fun isFile(): Boolean {
        val sFile = "/data/data/$packageName/databases/$DATABASE_NAME"
        val file = File(sFile)
        return file.exists()
    }
}