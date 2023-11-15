package com.daeseong.texttospeech_test

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import java.util.*

class TextToSpeechUtil(context: Context) {

    private val tag = TextToSpeechUtil::class.java.simpleName

    private var textToSpeech: TextToSpeech? = null

    init {
        try {
            if (textToSpeech == null) {
                textToSpeech = TextToSpeech(context.applicationContext) { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        setLanguage(Locale.KOREAN)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setLanguage(locale: Locale) {
        try {
            if (textToSpeech?.isLanguageAvailable(locale) == TextToSpeech.LANG_AVAILABLE) {
                textToSpeech?.language = locale
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun speak(text: String?) {
        try {
            if (textToSpeech != null) {
                if (textToSpeech!!.isSpeaking) {
                    textToSpeech!!.stop()
                }

                val queueMode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TextToSpeech.QUEUE_ADD
                } else {
                    TextToSpeech.QUEUE_ADD
                }

                textToSpeech!!.speak(text, queueMode, null, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() {
        try {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isLanguageAvailable(): Boolean {
        return try {
            textToSpeech?.isLanguageAvailable(Locale.KOREAN) == TextToSpeech.LANG_AVAILABLE
        } catch (e: Exception) {
            false
        }
    }
}
