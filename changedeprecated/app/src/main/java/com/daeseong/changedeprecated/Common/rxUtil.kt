package com.daeseong.changedeprecated.Common

import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object RxUtil {

    //convert AsyncTask<String, Void, String>
    fun getDataString(sUrl: String?): Observable<String?> {
        return Observable.fromCallable {
            try {
                HttpUtil.getDataString(sUrl)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //convert AsyncTask<String, Void, Bitmap>
    fun getDataBitmap(sUrl: String?): Observable<Bitmap?> {
        return Observable.fromCallable {
            try {
                HttpUtil.getDataBitmap(sUrl!!)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
