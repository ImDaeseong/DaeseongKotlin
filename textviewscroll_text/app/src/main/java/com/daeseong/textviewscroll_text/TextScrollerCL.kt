package com.daeseong.textviewscroll_text

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView

class TextScrollerCL(private val view1: View, private val view2: View) {

    companion object {
        private val tag = TextScrollerCL::class.java.simpleName
        private const val ANIMATION_DURATION: Long = 1000 // 애니메이션 지속 시간 (밀리초)
        private const val TEXT_CHANGE_INTERVAL: Long = 5000 // 텍스트 변경 간격 (밀리초)
    }

    private var currentIndex = 0
    private var isViewVisible = true
    private var isAnimating = false
    private var animator: ValueAnimator? = null
    private var isDirectUp = true
    private val urlApi = UrlApi.getInstance()

    init {
        setupViews()
        startAnimation()
    }

    //초기화
    private fun setupViews() {

        // 클릭 이벤트 설정
        val onClickListener = View.OnClickListener {
            val item = getCurrentItem()
            item?.let {
                Log.e(tag, "${it.getText()} : ${it.getUrl()}")
            }
        }
        view1.setOnClickListener(onClickListener)
        view2.setOnClickListener(onClickListener)

        // textView1 초기 설정
        urlApi.getItems().let { items ->

            // 다음 텍스트 설정
            if (items.isNotEmpty()) {
                updateNextText(view1, currentIndex)
            }
        }

        // view2 숨기기
        view2.visibility = View.INVISIBLE
        // view2를 view1의 높이만큼 아래로 이동
        view2.translationY = view1.height.toFloat()
    }

    // 애니메이션 시작
    private fun startAnimation() {

        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = ANIMATION_DURATION
            interpolator = AccelerateDecelerateInterpolator()

            addUpdateListener { animation ->
                // 뷰 위치 업데이트
                updateViewPositions(animation)
            }

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {

                    isAnimating = true
                    val itemCount = urlApi.getItems().size
                    if (itemCount > 0) {
                        getInvisibleView()?.let { view ->

                            // 다음 텍스트 설정
                            updateNextText(view, (currentIndex + 1) % itemCount)

                            // 뷰를 보이게 설정
                            view.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    // 뷰 교체
                    swapViews()
                    // 뷰 교체
                    animator?.startDelay = TEXT_CHANGE_INTERVAL - ANIMATION_DURATION
                    // 애니메이션 재시작
                    animator?.start()
                }
            })
            startDelay = TEXT_CHANGE_INTERVAL
            start()
        }
    }

    // TextView 위치 업데이트
    private fun updateViewPositions(animation: ValueAnimator) {
        val value = animation.animatedValue as Float
        val visibleView = getVisibleView()
        val invisibleView = getInvisibleView()

        if (isDirectUp) {

            //Log.e(tag, "스크롤 위로")
            visibleView?.translationY = -value * visibleView?.height!!
            invisibleView?.translationY = (1 - value) * invisibleView?.height!!

        } else {

            //Log.e(tag, "스크롤 아래로")
            visibleView?.translationY = value * visibleView?.height!!
            invisibleView?.translationY = -(1 - value) * invisibleView?.height!!
        }
    }

    // 다음 텍스트 내용 설정
    private fun updateNextText(view: View?, index: Int) {

        urlApi.getItems().let { items ->

            // 다음 텍스트 설정
            if (items.isNotEmpty()) {
                val textView = view?.findViewById<TextView>(if (view == view1) R.id.tv1 else R.id.tv2)
                textView?.text = items[index].getText()
            }
        }
    }

    // TextView 교체
    private fun swapViews() {
        val visibleView = getVisibleView()
        val invisibleView = getInvisibleView()

        visibleView?.visibility = View.INVISIBLE // 보이는 뷰 숨기기
        invisibleView?.visibility = View.VISIBLE // 숨겨진 뷰 보이기

        if (isDirectUp) {

            //Log.e(tag, "스크롤 위로")
            visibleView?.translationY = visibleView?.height!!.toFloat() // 위로 스크롤
            invisibleView?.translationY = 0f // 이동 없음
        } else {

            //Log.e(tag, "스크롤 아래로")
            visibleView?.translationY = -visibleView?.height!!.toFloat() // 아래로 스크롤
            invisibleView?.translationY = 0f // 이동 없음
        }

        urlApi.getItems().let { items ->
            if (items.isNotEmpty()) {
                currentIndex = (currentIndex + 1) % items.size
            }
        }

        // 보이는 뷰 상태 변경
        isViewVisible = !isViewVisible
    }

    // 현재 보이는 TextView 반환
    private fun getVisibleView(): View? {
        return if (isViewVisible) view1 else view2
    }

    // 현재 보이지 않는 TextView 반환
    private fun getInvisibleView(): View? {
        return if (isViewVisible) view2 else view1
    }

    // 현재 아이템
    private fun getCurrentItem(): UrlApi.UrlItem? {
        urlApi.getItems().let { items ->
            if (items.isNotEmpty()) {
                return items.getOrNull(currentIndex)
            }
        }
        return null
    }

    // 애니메이션 시작
    fun start() {
        if (animator?.isRunning == false && !isAnimating) {
            animator?.start()
            isAnimating = true
        }
    }

    // 애니메이션 중지
    fun stop() {
        if (animator?.isRunning == true && isAnimating) {
            animator?.cancel()
            isAnimating = false
        }
    }

    fun setDirect(isDirectUp: Boolean) {
        this.isDirectUp = isDirectUp
    }
}
