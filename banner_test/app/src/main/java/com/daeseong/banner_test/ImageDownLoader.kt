package com.daeseong.banner_test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.net.URL


class ImageDownLoader(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

    companion object {
        private val tag = ImageDownLoader::class.java.name
    }

    override fun doInBackground(vararg params: String): Bitmap {

        var bitmap: Bitmap? = null
        try {
            bitmap = BitmapFactory.decodeStream(URL(params[0]).openStream())
        } catch (e: Exception) {
            e.printStackTrace()
            cancel(true)
        }
        return bitmap!!
    }

    override fun onPostExecute(bitmap: Bitmap) {
        try {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCancelled() {
        Log.e(tag, "onCancelled")
    }

}