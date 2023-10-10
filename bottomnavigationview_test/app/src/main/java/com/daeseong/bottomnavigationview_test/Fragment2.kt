package com.daeseong.bottomnavigationview_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class Fragment2 : Fragment() {

    companion object {

        private val tag = Fragment2::class.java.simpleName
        private var webView: WebView? = null

        fun newInstance(): Fragment2 {
            return Fragment2()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //Log.e(tag, "onCreateView")

        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Log.e(tag, "onViewCreated")

        webView = view.findViewById<View>(R.id.webview) as WebView
        webView!!.settings.javaScriptEnabled = true
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl("https://www.youtube.com")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        //Log.e(tag, "onDestroyView")
    }

    override fun onPause() {
        super.onPause()

        //Log.e(tag, "Fragment 가려질때")
    }

    override fun onResume() {
        super.onResume()

        //Log.e(tag, "Fragment 보일때")
    }
}