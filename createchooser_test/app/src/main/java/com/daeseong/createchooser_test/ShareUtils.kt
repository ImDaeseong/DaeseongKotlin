package com.daeseong.createchooser_test

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import java.util.ArrayList

object ShareUtils {

    private val tag: String = ShareUtils::class.java.simpleName

    fun shareLink(context: Context, title: String, link: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, link)

            val chooserIntent = Intent.createChooser(intent, title)
            context.startActivity(chooserIntent)
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    fun shareText(context: Context, title: String, text: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, title)
            intent.putExtra(Intent.EXTRA_TEXT, text)

            val chooserIntent = Intent.createChooser(intent, title)
            context.startActivity(chooserIntent)
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    fun shareImage(context: Context, title: String, uri: Uri) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            val chooserIntent = Intent.createChooser(intent, title)
            context.startActivity(chooserIntent)
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    fun shareMultiText(context: Context, title: String, text: String, uri: Uri) {
        try {
            val imageUris = ArrayList<Uri>()
            imageUris.add(uri)

            val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, title)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
            val chooserIntent = Intent.createChooser(intent, title)
            context.startActivity(chooserIntent)
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }
}
