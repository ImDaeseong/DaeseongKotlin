package com.daeseong.paging_test

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.daeseong.paging_test.API.SearchApi
import com.daeseong.paging_test.Common.GetStringTask
import com.daeseong.paging_test.Model.Recycler5Adapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class Main5Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    private lateinit var fb1: FloatingActionButton

    private lateinit var swl1: SwipeRefreshLayout
    private lateinit var rv1: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: Recycler5Adapter

    private var sResult = ""

    private var nTotalPage = 0 // 총 페이지
    private var nTotalCount = 0 // 데이터 개수
    private val nPageSize = 10 // 페이지에 보여줄 개수

    private var sUrl = ""
    private val sSearchkey = "android"
    private var nIndex = 0

    private lateinit var thread: HandlerThread
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        initThread()

        initTotalpage()

        initData()

        fb1 = findViewById(R.id.fb1)

        swl1 = findViewById(R.id.swl1)

        rv1 = findViewById(R.id.rv1)
        manager = LinearLayoutManager(this)
        rv1.layoutManager = manager

        adapter = Recycler5Adapter(this)
        rv1.adapter = adapter

        rv1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                when (newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING -> Log.e(tag, "스크롤 상태가 드래그 될때")
                    RecyclerView.SCROLL_STATE_IDLE -> Log.e(tag, "스크롤이 정지되어 있는 상태")
                    RecyclerView.SCROLL_STATE_SETTLING -> Log.e(tag,"위든 아래든 마지막 위치에 도달")
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val nlastItem = (rv1.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() + 1
                val nTotalCount = rv1.adapter!!.itemCount

                if (nlastItem == nTotalCount) {

                    Log.e(tag, "스크롤 마지막에 도착")

                    runOnUiThread {
                        fb1.visibility = View.VISIBLE
                    }
                }

            }
        })

        swl1.setOnRefreshListener(OnRefreshListener {

            swl1.isRefreshing = true

            ++nIndex

            if (nIndex > nTotalPage) {
                nIndex = nTotalPage
                swl1.isRefreshing = false
                return@OnRefreshListener
            }

            sUrl = String.format("%s&q=%s&page=%d", ConstantsUrl.sUrl2, sSearchkey, nIndex)
            //Log.e(tag, "sUrl:$sUrl")

            sResult = GetStringTask().execute(sUrl)!!
            parsingData(nIndex, sResult)
            swl1.isRefreshing = false
        })

        fb1.setOnClickListener(View.OnClickListener {

            val list = SearchApi.getInstance().getSearch("Android")
            if (list != null) {
                adapter.add(list)
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()

        SearchApi.getInstance().clear()
        StopThread()
    }

    private fun initThread() {

        try {

            thread = HandlerThread("paging")
            thread.start()
            handler = object : Handler(thread.looper) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)

                }
            }
        } catch (ex: java.lang.Exception) {
            ex.message.toString()
        }
    }

    private fun StopThread() {

        try {
            if (::thread.isInitialized) {
                thread.looper.quit()
                thread.quit()
            }
        } catch (ex: java.lang.Exception) {
            ex.message.toString()
        } finally {
        }
    }

    private fun initTotalpage() {

        handler.post {

            try {

                sUrl = String.format("%s&q=%s", ConstantsUrl.sUrl1, sSearchkey)
                sResult = GetStringTask().execute(sUrl)!!

                if (!TextUtils.isEmpty(sResult)) {
                    val jsonObject = JSONObject(sResult)
                    if (jsonObject.has("total_count")) {
                        nTotalCount = jsonObject.getInt("total_count")
                    }
                }

            } catch (ex: java.lang.Exception) {
                Log.e(tag, ex.message.toString())
            }

            //전체 페이지 계산
            nTotalPage = nTotalCount / nPageSize + 1
            Log.e(tag, "총 페이지:$nTotalPage 데이터 개수:$nTotalCount")
        }
    }

    private fun initData() {

        handler.post {

            ++nIndex

            sUrl = String.format("%s&q=%s&page=%d", ConstantsUrl.sUrl2, sSearchkey, nIndex)
            //Log.e(tag, "sUrl:$sUrl")

            sResult = GetStringTask().execute(sUrl)!!
            parsingData(nIndex, sResult)
        }
    }

    private fun parsingData(nIndex: Int, sResult: String) {
        try {

            if (!TextUtils.isEmpty(sResult)) {

                val jsonObject = JSONObject(sResult)
                val list: MutableList<SearchApi.itemData> = ArrayList()
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

                    val item = SearchApi.itemData()
                    item.setItem(ID, NAME, HTMLURL, Createdat)
                    list.add(item)
                }

                if (SearchApi.getInstance().setMap(nIndex, list)) {
                    runOnUiThread {
                        adapter.addAll(list)
                    }
                }

            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }
}