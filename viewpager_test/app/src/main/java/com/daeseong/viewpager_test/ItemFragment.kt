package com.daeseong.viewpager_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment


class ItemFragment : Fragment() {

    companion object {

        fun newInstance(position: Int): ItemFragment {

            val args = Bundle()
            args.putInt("position", position)

            val fragment = ItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.pager6_adapter, container, false)

        val imageView: ImageView = view.findViewById<View>(R.id.imageview6) as ImageView
        imageView.setImageResource(arguments!!.getInt("position"))

        return view
    }

}
