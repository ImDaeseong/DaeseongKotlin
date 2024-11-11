package com.daeseong.bottomsheetdialog_test

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener

class Main9Activity : AppCompatActivity() {

    // UI 요소
    private lateinit var bottomSheet: ConstraintLayout
    private lateinit var button1: Button
    private lateinit var bottomSheetHandle: View

    // 드래그 관련 변수
    private var initialY = 0f
    private var lastY = 0f
    private var bottomSheetHeight = 0
    private var isDragging = false
    private var startTime = 0L
    private var dragVelocity = 0f
    private var lastDragDirection = 0 // 1: 아래로, -1: 위로, 0: 초기 상태

    companion object {
        private const val ANIMATION_DURATION = 300L // 애니메이션 지속 시간
        private const val DISMISS_THRESHOLD = 0.4f // 바텀 시트를 닫기 위한 임계값 (전체 높이의 40%)
        private const val VELOCITY_THRESHOLD = 800f // 빠른 스와이프 감지를 위한 속도 임계값
        private const val DRAG_SENSITIVITY = 0.8f // 드래그 민감도 (낮을수록 덜 민감)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        initializeViews()
        setupBottomSheet()
        setupClickListeners()
    }

    // UI 요소 초기화
    private fun initializeViews() {
        bottomSheet = findViewById(R.id.bottomSheet)
        button1 = findViewById(R.id.button1)
        bottomSheetHandle = findViewById(R.id.bottomSheetHandle)
        bottomSheetHandle.isClickable = true
        bottomSheetHandle.isFocusable = true
    }

    // 바텀 시트 초기 설정
    private fun setupBottomSheet() {
        bottomSheet.visibility = View.GONE

        // 바텀 시트의 높이를 측정
        bottomSheet.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                bottomSheetHeight = bottomSheet.height
                bottomSheet.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    // 클릭 리스너 설정
    private fun setupClickListeners() {
        // 버튼 클릭 시 바텀 시트 표시/숨김 전환
        button1.setOnClickListener {
            if (bottomSheet.visibility == View.GONE) {
                showBottomSheet()
            } else {
                hideBottomSheet()
            }
        }

        // 바텀 시트 핸들의 터치 이벤트 처리
        bottomSheetHandle.setOnTouchListener { _, event ->
            handleTouchEvent(event)
        }
    }

    // 터치 이벤트 처리
    private fun handleTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 드래그 시작
                isDragging = true
                initialY = event.rawY
                lastY = event.rawY
                startTime = System.currentTimeMillis()
                lastDragDirection = 0
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (isDragging) {
                    // 드래그 중
                    val deltaY = (event.rawY - lastY) * DRAG_SENSITIVITY
                    moveBottomSheet(deltaY)
                    updateDragMetrics(event)
                    return true
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (isDragging) {
                    // 드래그 종료
                    isDragging = false
                    handleDragEnd()
                    return true
                }
            }
        }
        return false
    }

    // 바텀 시트 이동
    private fun moveBottomSheet(deltaY: Float) {
        val newTranslationY = bottomSheet.translationY + deltaY
        if (newTranslationY >= 0) {
            bottomSheet.translationY = newTranslationY
        }
    }

    // 드래그 관련 지표 업데이트
    private fun updateDragMetrics(event: MotionEvent) {
        val deltaY = event.rawY - lastY
        lastDragDirection = if (deltaY > 0) 1 else -1

        val dragDuration = System.currentTimeMillis() - startTime
        dragVelocity = if (dragDuration > 0) {
            (event.rawY - initialY) / dragDuration * 1000
        } else {
            0f
        }

        lastY = event.rawY
    }

    // 드래그 종료 처리
    private fun handleDragEnd() {
        if (shouldDismissBottomSheet()) {
            hideBottomSheetImmediately()
        } else {
            resetBottomSheet()
        }
    }

    // 바텀 시트를 닫아야 하는지 결정하는 함수
    private fun shouldDismissBottomSheet(): Boolean {
        // 충분히 아래로 드래그되었는지
        val isDraggedFarEnough = bottomSheet.translationY > bottomSheetHeight * DISMISS_THRESHOLD

        // 마지막 드래그 방향이 아래쪽인지
        val isLastDragDirectionDown = lastDragDirection > 0

        // 드래그 속도가 임계값을 초과했는지
        val isDragVelocityHigh = dragVelocity > VELOCITY_THRESHOLD

        // 바텀 시트를 닫아야 하는 경우:
        // 1. 충분히 아래로 드래그되었고, 마지막 드래그 방향이 아래쪽인 경우
        // 2. 드래그 속도가 빠르고, 마지막 드래그 방향이 아래쪽인 경우
        return (isDraggedFarEnough && isLastDragDirectionDown) ||
                (isDragVelocityHigh && isLastDragDirectionDown)
    }

    // 바텀 시트를 즉시 숨김 (애니메이션 없이)
    private fun hideBottomSheetImmediately() {
        bottomSheet.visibility = View.GONE
        bottomSheet.translationY = 0f
    }

    // 바텀 시트를 원래 위치로 리셋
    private fun resetBottomSheet() {
        val currentTranslationY = bottomSheet.translationY
        animateBottomSheet(currentTranslationY, 0f, ANIMATION_DURATION)
    }

    // 바텀 시트 표시
    private fun showBottomSheet() {
        bottomSheet.visibility = View.VISIBLE
        bottomSheet.translationY = bottomSheetHeight.toFloat()
        animateBottomSheet(bottomSheetHeight.toFloat(), 0f, ANIMATION_DURATION)
    }

    // 바텀 시트 숨김 (애니메이션 사용)
    private fun hideBottomSheet() {
        animateBottomSheet(bottomSheet.translationY, bottomSheetHeight.toFloat(), ANIMATION_DURATION) {
            bottomSheet.visibility = View.GONE
            bottomSheet.translationY = 0f
        }
    }

    // 바텀 시트 애니메이션
    private fun animateBottomSheet(fromTranslationY: Float, toTranslationY: Float, duration: Long, endAction: (() -> Unit)? = null) {
        val animator = ValueAnimator.ofFloat(fromTranslationY, toTranslationY)
        animator.addUpdateListener { animation ->
            bottomSheet.translationY = animation.animatedValue as Float
        }

        animator.duration = duration
        animator.interpolator = DecelerateInterpolator()
        animator.start()

        animator.addListener(onEnd = {
            endAction?.invoke()
        })
    }
}
