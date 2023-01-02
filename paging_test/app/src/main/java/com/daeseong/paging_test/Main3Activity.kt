package com.daeseong.paging_test

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.paging_test.API.SearchApi
import com.daeseong.paging_test.API.SearchApi.itemData
import com.daeseong.paging_test.Common.DateTime.getOneDayago
import com.daeseong.paging_test.Common.GetStringTask
import org.json.JSONObject

class Main3Activity : AppCompatActivity() {

    companion object {
        private val tag = Main3Activity::class.java.simpleName
    }

    private var tv1: TextView? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null

    private var sResult: String? = ""

    private var nTotalPage = 0 // 총 페이지
    private var nTotalCount = 0 // 데이터 개수
    private val nPageSize = 10 // 페이지에 보여줄 개수

    private var sUrl = ""
    private val sSearchkey = "android"

    private var thread: HandlerThread? = null
    private var handler: Handler? = null
    private var reshandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        initThread()

        initTotalpage()

        tv1 = findViewById<View>(R.id.tv1) as TextView
        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            try {

                for (i in 1..nTotalPage) {
                    handler!!.sendEmptyMessageDelayed(i, i.toLong())
                }

            } catch (ex: Exception) {
                ex.message.toString()
            }
        }

        button2 = findViewById<View>(R.id.button2) as Button
        button2!!.setOnClickListener {
            parsingDataResult(1)
        }

        button3 = findViewById<View>(R.id.button3) as Button
        button3!!.setOnClickListener {
            parsingDataResult(2)
        }

        button4 = findViewById<View>(R.id.button4) as Button
        button4!!.setOnClickListener {
            parsingDataResult(3)
        }

        button5 = findViewById<View>(R.id.button5) as Button
        button5!!.setOnClickListener {
            parsingDataResult(4)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        StopThread()
    }

    private fun initThread() {

        try {

            thread = HandlerThread("paging")
            thread!!.start()
            handler = Handler(thread!!.looper, PageCallback())
            reshandler = object : Handler(mainLooper) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)

                    Log.e(tag, msg.what.toString())
                    Log.e(tag, (msg.obj as String))
                }
            }

        } catch (ex: Exception) {
            ex.message.toString()
        }
    }

    private fun StopThread() {

        try {

            if (reshandler != null) {
                reshandler!!.removeMessages(0)
            }

            if (handler != null) {
                handler!!.removeCallbacks(initTotalpage)
            }

            if (thread != null) {
                thread!!.looper.quit()
                thread!!.quit()
            }

        } catch (ex: Exception) {
            ex.message.toString()
        } finally {
            handler = null
            thread = null
        }
    }

    private fun initTotalpage() {
        handler!!.postDelayed(initTotalpage, 10)
    }

    private fun parsingData(nIndex: Int, sResult: String?) {

        try {

            if (!TextUtils.isEmpty(sResult)) {

                val jsonObject = JSONObject(sResult)
                val list: MutableList<itemData> = ArrayList()

                val jsonArray = jsonObject.getJSONArray("items")
                for (j in 0 until jsonArray.length()) {

                    val JSonObj = jsonArray.getJSONObject(j)

                    var ID: String? = ""
                    if (JSonObj.has("id")) ID = JSonObj.getString("id")

                    var NAME: String? = ""
                    if (JSonObj.has("name")) NAME = JSonObj.getString("name")

                    var Createdat: String? = ""
                    if (JSonObj.has("created_at")) Createdat = JSonObj.getString("created_at")

                    var HTMLURL = ""
                    if (JSonObj.has("owner")) {
                        val sOwner = JSonObj.getString("owner")
                        val jsonObj = JSONObject(sOwner)
                        if (jsonObj.has("html_url")) {
                            HTMLURL = jsonObj.getString("html_url")
                        }
                    }

                    val item = itemData()
                    item.setItem(ID, NAME, HTMLURL, Createdat)
                    list.add(item)
                }
                SearchApi.getInstance().setMap(nIndex, list)
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun parsingDataResult(nIndex: Int) {

        val map = SearchApi.getInstance().getItem()
        if (nIndex == 1) {
            for (key in map.keys) {
                val list = map[key]
                if (list != null) {
                    for (i in list) {
                        Log.e(tag, key.toString() + " " + i.NAME)
                    }
                }
            }

        } else if (nIndex == 2) {
            for (key in map.keys) {
                for (i in map[key]!!.indices) {
                    Log.e(tag, "$key " + map[key]!![i].NAME)
                }
            }
        } else if (nIndex == 3) {
            val nKey = 2
            val list = SearchApi.getInstance().getItem(nKey)
            if (list != null) {
                for (i in list) {
                    Log.e(tag, nKey.toString() + " " + i.NAME)
                }
            }

        } else if (nIndex == 4) {
            for (key in map.keys) {
                val list = map[key]
                if (list != null) {
                    for (i in list) {
                        if (i.NAME.toString().indexOf("Android") > -1) {
                            Log.e(tag, key.toString() + " " + i.NAME)
                        }
                    }
                }
            }

        }
    }

    private val initTotalpage = Runnable {

        try {

            sUrl = String.format("%s&q=%s:created:>%s", ConstantsUrl.sUrl1, sSearchkey, getOneDayago())
            sResult = GetStringTask().execute(sUrl)
            if (!TextUtils.isEmpty(sResult)) {
                val jsonObject = JSONObject(sResult)
                if (jsonObject.has("total_count")) {
                    nTotalCount = jsonObject.getInt("total_count")
                }
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }

        //전체 페이지 계산
        nTotalPage = nTotalCount / nPageSize + 1
        Log.e(tag, "총 페이지:$nTotalPage 데이터 개수:$nTotalCount")
    }

    internal inner class PageCallback : Handler.Callback {
        override fun handleMessage(message: Message): Boolean {

            try {

                val i = message.what
                sUrl = String.format("%s&q=%s:created:>%s&PAGE=%d", ConstantsUrl.sUrl2, sSearchkey, getOneDayago(), i)
                sResult = GetStringTask().execute(sUrl)
                parsingData(i, sResult)

                val msg = Message()
                msg.what = message.what
                msg.obj = sResult
                reshandler!!.sendMessage(msg)

            } catch (ex: Exception) {
                ex.message.toString()
            }
            return false
        }
    }
}