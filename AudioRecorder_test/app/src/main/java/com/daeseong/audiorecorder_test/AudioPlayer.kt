package com.daeseong.audiorecorder_test

import android.media.MediaPlayer
import android.util.Log

class AudioPlayer private constructor() {

    companion object {

        private val tag = AudioPlayer::class.java.name

        private var mediaPlayer: MediaPlayer? = null

        @JvmStatic
        val instance: AudioPlayer by lazy { AudioPlayer() }
    }

    private var onMediaPlayerListener: OnMediaPlayerListener? = null

    interface OnMediaPlayerListener {
        fun onCompletion(bComplete: Boolean)
        fun onPrepared(mDuration: Int)
    }

    fun release() {
        try {
            removeListener()

            mediaPlayer?.release()
            mediaPlayer = null
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    fun play(sPath: String, onMediaPlayerListener: OnMediaPlayerListener) {
        try {
            mediaPlayer = mediaPlayer ?: MediaPlayer()

            mediaPlayer?.let {
                it.reset()
                this.onMediaPlayerListener = onMediaPlayerListener
                it.setDataSource(sPath)
                it.prepare()

                it.setOnCompletionListener { mp ->
                    onMediaPlayerListener.onCompletion(true)
                }

                it.setOnPreparedListener { mp ->
                    onMediaPlayerListener.onPrepared(mp.duration)
                }

                it.setOnErrorListener { mp, what, extra ->
                    onMediaPlayerListener.onCompletion(false)
                    false
                }

                it.start()
            }
        } catch (ex: Exception) {
            onMediaPlayerListener.onCompletion(false)
            Log.e(tag, ex.message.toString())
        }
    }

    fun start() {
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
                reset()
            }
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun getCurrentPosition(): Int {
        return mediaPlayer?.currentPosition ?: 0
    }

    fun getDuration(): Int {
        return mediaPlayer?.duration ?: 0
    }

    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
    }

    private fun removeListener() {
        onMediaPlayerListener = null
    }
}