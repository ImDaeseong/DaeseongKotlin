package com.daeseong.uidrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class MainTab5Fragment : Fragment() {

    companion object {
        private val tag = MainTab5Fragment::class.java.simpleName
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_tab5, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
