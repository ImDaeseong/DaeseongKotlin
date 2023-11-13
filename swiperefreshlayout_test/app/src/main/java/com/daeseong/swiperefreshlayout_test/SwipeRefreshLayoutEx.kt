package com.daeseong.swiperefreshlayout_test

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class SwipeRefreshLayoutEx @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : SwipeRefreshLayout(context, attrs) {

    private var onRefreshListener: OnRefreshListener? = null

    init {
        init()
    }

    private fun init() {
        setColorSchemeResources(
            R.color.purple_500,
            R.color.purple_500,
            R.color.purple_500,
            R.color.purple_500
        )

        // 스와이프 민감도 설정(기본:120)
        setDistanceToTriggerSync(20)
        setSize(DEFAULT)

        // 스와이프 프로그래스바 위치 - 숨김
        // setProgressViewOffset(true, -10000, -10000)
    }

    override fun setOnRefreshListener(listener: OnRefreshListener?) {
        super.setOnRefreshListener(listener)
        onRefreshListener = listener
    }

    fun hideProgressBar(bProgressHide: Boolean) {
        if (bProgressHide) {
            // 스와이프 프로그래스바 위치 - 숨김
            setProgressViewOffset(true, -10000, -10000)
        }
    }
}
