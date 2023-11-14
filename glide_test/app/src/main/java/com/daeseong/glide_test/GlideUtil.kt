package com.daeseong.glide_test

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object GlideUtil {

    private val tag: String = GlideUtil::class.java.simpleName

    fun load(context: Context?, imageView: ImageView, sUrl: String) {
        context ?: return
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
