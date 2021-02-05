package com.daeseong.glide_test

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class GlideUtil {

    private val tag: String = GlideUtil::class.java.simpleName;

    companion object {
        private var instance: GlideUtil? = null
        fun getInstance(): GlideUtil {
            if (instance == null) {
                instance = GlideUtil()
            }
            return instance as GlideUtil
        }
    }

    fun load(context: Context, imageView: ImageView, sUrl: String) {

        if (imageView != null) {

            if (context != null) {

                Glide.with(context)
                    .load(sUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                    )
                    .into(imageView)
            }
        }
    }

}