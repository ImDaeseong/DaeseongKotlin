package com.daeseong.uidrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class MainTab4Fragment : Fragment() {

    companion object {
        private val tag = MainTab4Fragment::class.java.simpleName
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_tab4, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
