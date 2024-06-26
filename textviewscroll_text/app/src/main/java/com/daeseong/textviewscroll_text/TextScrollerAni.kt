package com.daeseong.textviewscroll_text

import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class TextScrollerAni(private val tv1: TextView, private val tv2: TextView) {

    companion object {
        private val tag = TextScrollerAni::class.java.simpleName
        private const val SCROLL_DELAY: Long = 5000 // 스크롤 간 지연 시간 (5초)
        private const val ANIMATION_DURATION: Long = 1000 // 애니메이션 지속 시간 (1초)
    }

    private val urlApi = UrlApi.getInstance()
    private val items: List<UrlApi.UrlItem> = urlApi.getItems()
    private var currentItemIndex = 0
    private val handler = Handler()
    private lateinit var scrollRunnable: Runnable
    private lateinit var clickListener: View.OnClickListener

    init {
        initializeTextViews()
        setupHandler()
        setupClickListener()
    }

    // TextView 초기화
    private fun initializeTextViews() {
        if (items.isNotEmpty()) {
            tv1.text = items[currentItemIndex].getText()
            tv2.text = items[(currentItemIndex + 1) % items.size].getText()
        }
    }

    // Handler 및 Runnable 설정
    private fun setupHandler() {
        scrollRunnable = Runnable {
            scrollTexts()
            handler.postDelayed(scrollRunnable, SCROLL_DELAY)
        }
    }

    // 클릭 리스너 설정
    private fun setupClickListener() {
        clickListener = View.OnClickListener {
            getCurrentItem()?.let { currentItem ->
                Log.e(tag, "${currentItem.getText()} : ${currentItem.getUrl()}")
            }
        }
        tv1.setOnClickListener(clickListener)
        tv2.setOnClickListener(clickListener)
    }

    // 애니메이션 시작
    fun start() {
        handler.postDelayed(scrollRunnable, SCROLL_DELAY)
    }

    // 애니메이션 중지
    fun stop() {
        handler.removeCallbacks(scrollRunnable)
    }

    // 현재 아이템 반환
    private fun getCurrentItem(): UrlApi.UrlItem? {
        return if (items.isNotEmpty()) {
            items[currentItemIndex]
        } else {
            null
        }
    }

    // 텍스트 스크롤 애니메이션 실행
    private fun scrollTexts() {
        val anim1 = TranslateAnimation(0f, 0f, 0f, -tv1.height.toFloat())
        anim1.duration = ANIMATION_DURATION
        anim1.fillAfter = true
        tv1.startAnimation(anim1)

        val anim2 = TranslateAnimation(0f, 0f, tv1.height.toFloat(), 0f)
        anim2.duration = ANIMATION_DURATION
        anim2.fillAfter = true
        tv2.startAnimation(anim2)

        handler.postDelayed({
            updateTextViews()
        }, ANIMATION_DURATION)
    }

    // TextView 업데이트
    private fun updateTextViews() {
        tv1.text = tv2.text
        tv1.clearAnimation()
        tv2.clearAnimation()

        // tv2를 화면 아래로 재배치
        val params = tv2.layoutParams as ConstraintLayout.LayoutParams
        params.topToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        tv2.layoutParams = params

        // 다음 사이클을 위해 currentItemIndex 업데이트
        currentItemIndex = (currentItemIndex + 1) % items.size
        tv2.text = items[(currentItemIndex + 1) % items.size].getText()
    }
}
