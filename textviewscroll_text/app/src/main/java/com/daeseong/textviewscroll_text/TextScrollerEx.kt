package com.daeseong.textviewscroll_text

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView

class TextScrollerEx(private val textView1: TextView, private val textView2: TextView) {

    private val tag = TextScrollerEx::class.java.simpleName

    private val ANIMATION_DURATION: Long = 500 // 애니메이션 지속 시간 (밀리초)
    private val TEXT_CHANGE_INTERVAL: Long = 5000 // 텍스트 변경 간격 (밀리초)

    private var currentIndex = 0
    private var isTextView1Visible = true // 현재 textView1이 보이는지 여부
    private var isAnimating = false // 애니메이션 진행 중 여부
    private lateinit var animator: ValueAnimator

    private var isDirectUp = true

    init {
        setupViews()
        startAnimation()
    }

    //초기화
    private fun setupViews() {

        //클릭 이벤트
        val onClickListener = View.OnClickListener {
            val item = getCurrentItem()
            Log.e(tag, "${item?.getText()} : ${item?.getUrl()}")
        }
        textView1.setOnClickListener(onClickListener)
        textView2.setOnClickListener(onClickListener)

        //textView1 설정
        val items = UrlApi.getInstance().getItems()
        if (items.isNotEmpty()) {
            textView1.text = items[0].getText()
        }
        textView2.visibility = View.INVISIBLE
        textView2.translationY = textView1.height.toFloat()
    }

    // 애니메이션 시작
    private fun startAnimation() {

        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = ANIMATION_DURATION
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation -> updateTextViewPositions(animation) }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                isAnimating = true
                val invisibleTextView = getInvisibleTextView()
                updateNextText(invisibleTextView)
                invisibleTextView.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {
                swapTextViews()
                animator.startDelay = TEXT_CHANGE_INTERVAL - ANIMATION_DURATION
                animator.start()
            }
        })
        animator.startDelay = TEXT_CHANGE_INTERVAL
        animator.start()
    }

    // TextView 위치 업데이트
    private fun updateTextViewPositions(animation: ValueAnimator) {

        val value = animation.animatedValue as Float
        val visibleTextView = getVisibleTextView()
        val invisibleTextView = getInvisibleTextView()

        if (isDirectUp) {

            //Log.e(tag, "스크롤 위로")
            visibleTextView.translationY = -value * visibleTextView.height
            invisibleTextView.translationY = (1 - value) * invisibleTextView.height

        } else {

            //Log.e(tag, "스크롤 아래로")
            visibleTextView.translationY = value * visibleTextView.height
            invisibleTextView.translationY = -((1 - value)) * invisibleTextView.height
        }
    }

    // 다음 텍스트 내용 설정
    private fun updateNextText(textView: TextView) {
        val items = UrlApi.getInstance().getItems()
        if (items.isNotEmpty()) {
            val nextIndex = (currentIndex + 1) % items.size
            textView.text = items[nextIndex].getText()
        }
    }

    // TextView 교체
    private fun swapTextViews() {

        val visibleTextView = getVisibleTextView()
        val invisibleTextView = getInvisibleTextView()

        visibleTextView.visibility = View.INVISIBLE
        invisibleTextView.visibility = View.VISIBLE

        if (isDirectUp) {

            //Log.e(tag, "스크롤 위로")
            visibleTextView.translationY = visibleTextView.height.toFloat()
            invisibleTextView.translationY = 0f

        } else {

            //Log.e(tag, "스크롤 아래로")
            visibleTextView.translationY = -visibleTextView.height.toFloat()
            invisibleTextView.translationY = 0f
        }

        val items = UrlApi.getInstance().getItems()
        if (items.isNotEmpty()) {
            currentIndex = (currentIndex + 1) % items.size
        }
        isTextView1Visible = !isTextView1Visible
    }

    // 현재 보이는 TextView 반환
    private fun getVisibleTextView(): TextView {
        return if (isTextView1Visible) textView1 else textView2
    }

    // 현재 보이지 않는 TextView 반환
    private fun getInvisibleTextView(): TextView {
        return if (isTextView1Visible) textView2 else textView1
    }

    // 현재 아이템
    private fun getCurrentItem(): UrlApi.UrlItem? {
        val items = UrlApi.getInstance().getItems()
        return if (items.isNotEmpty()) {
            items[currentIndex]
        } else {
            null
        }
    }

    // 애니메이션 시작
    fun start() {
        if (::animator.isInitialized && !isAnimating) {
            animator.start()
            isAnimating = true
        }
    }

    // 애니메이션 중지
    fun stop() {
        if (::animator.isInitialized && isAnimating) {
            animator.cancel()
            isAnimating = false
        }
    }

    fun setDirect(isDirectUp: Boolean) {
        this.isDirectUp = isDirectUp
    }
}