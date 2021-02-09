package com.daeseong.texttospeech_test

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import java.util.*


class TextToSpeechUtil(context: Context?) {

    private val tag = TextToSpeechUtil::class.java.simpleName

    private var textToSpeech: TextToSpeech? = null

    init {

        try {
            if (textToSpeech == null) {
                textToSpeech = TextToSpeech(context) { i ->
                    if (i == TextToSpeech.SUCCESS) {
                        if (textToSpeech!!.isLanguageAvailable(Locale.KOREAN) == TextToSpeech.LANG_AVAILABLE) {
                            textToSpeech!!.language = Locale.KOREAN
                        }
                    }
                    //textToSpeech.setLanguage(Locale.KOREAN);
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Speak(sText: String?) {

        try {

            if (textToSpeech != null) {

                if (textToSpeech!!.isSpeaking) {
                    textToSpeech!!.stop()
                }

                //textToSpeech.setSpeechRate(1.0f);//속도
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech!!.speak(sText, TextToSpeech.QUEUE_ADD, null, null) //UtteranceProgressListener 이벤트 받지 않기 위해 null
                } else {
                    textToSpeech!!.speak(sText, TextToSpeech.QUEUE_ADD, null) //UtteranceProgressListener 이벤트 받지 않기 위해 null
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun Stop() {

        try {

            if (textToSpeech != null) {
                textToSpeech!!.stop()
                textToSpeech!!.shutdown()
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun isLanguageAvailable(): Boolean {

        var bAvailable = false

        try {

            if (textToSpeech!!.isLanguageAvailable(Locale.KOREAN) == TextToSpeech.LANG_AVAILABLE) {
                bAvailable = true
            }
        } catch (e: java.lang.Exception) {
        }
        return bAvailable
    }

}