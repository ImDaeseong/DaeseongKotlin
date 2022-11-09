package com.daeseong.changedeprecated.Common

import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object rxUtil {

    //convert AsyncTask<String, Void, String>
    open fun GetDataString(sUrl: String?): Observable<String?>? {

        return Observable.fromCallable {

            var sResult = ""
            try {
                sResult = HttpUtil.GetDataString(sUrl)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            sResult
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //convert AsyncTask<String, Void, Bitmap>
    open fun GetDataBitmap(sUrl: String?): Observable<Bitmap?>? {

        return Observable.fromCallable {

            var bm: Bitmap? = null
            try {
                bm = HttpUtil.GetDataBitmap(sUrl!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            bm!!
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
