package com.daeseong.changedeprecated.Common

import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object RxUtil {

    fun getDataString(sUrl: String?): Observable<String> {
        return if (sUrl.isNullOrEmpty()) {
            Observable.just("")
        } else {
            Observable.fromCallable<String> {
                HttpUtil.getDataString(sUrl) ?: ""
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn {
                    it.printStackTrace()
                    ""
                }
        }
    }

    fun getDataBitmap(sUrl: String?): Observable<Bitmap> {
        return if (sUrl.isNullOrEmpty()) {
            Observable.error(NullPointerException("null"))
        } else {
            Observable.fromCallable<Bitmap> {
                HttpUtil.getDataBitmap(sUrl) ?: throw NullPointerException("null")
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn {
                    it.printStackTrace()
                    Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                }
        }
    }
}
