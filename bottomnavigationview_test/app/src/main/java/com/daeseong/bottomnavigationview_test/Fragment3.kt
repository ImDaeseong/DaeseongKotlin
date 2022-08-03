package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class Fragment3 : Fragment() {

    companion object {
        private val tag = Fragment3::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onPause() {
        super.onPause()

        Log.e(tag, "Fragment3 가려질때")
    }

    override fun onResume() {
        super.onResume()

        Log.e(tag, "Fragment3 보일때")
    }
}