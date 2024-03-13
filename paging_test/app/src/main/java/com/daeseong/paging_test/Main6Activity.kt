package com.daeseong.paging_test

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daeseong.paging_test.API.SearchApi
import com.daeseong.paging_test.API.SearchApi.itemData
import com.daeseong.paging_test.Common.HttpUtil.GetDataString
import com.daeseong.paging_test.Model.BaseRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.simpleName

    private lateinit var swl1: SwipeRefreshLayout
    private lateinit var nsv1: NestedScrollView

    private lateinit var fb1: FloatingActionButton

    private lateinit var rv1: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: BaseRecyclerAdapter
    private lateinit var progressbar1: ProgressBar

    private var nTotalPage = -1  // 총 페이지
    private var nTotalCount = 0  // 데이터 개수
    private var nIndex = 0      // 현재 페이지
    private var nPageSize = 1   // 페이지에 보여줄 개수
    private var sSearchkey = "android"

    private lateinit var thread: HandlerThread
    private lateinit var handler: Handler

    //paging 처리
    private val RESULT_PASING_SEARCH = 1
    private val RESULT_PASING_SEARCHEND = 2

    private var list: MutableList<SearchApi.itemData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.daeseong.paging_test.R.layout.activity_main6)

        initThread();

        progressbar1 = findViewById(R.id.progressbar1)
        progressbar1.visibility = View.GONE;

        //당겨서 새로고침
        swl1 = findViewById(R.id.swl1)
        swl1.setDistanceToTriggerSync(500) //스와이프 민감도 설정(기본:120)
        swl1.setColorSchemeColors(ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.white))
        swl1.setOnRefreshListener {

            try {

                swl1.isRefreshing = true

                //데이터 초기화
                nTotalPage = -1
                nTotalCount = 0
                nIndex = 0
                adapter.clear()

                requestSearchPaging()

                if (swl1.isRefreshing) {
                    swl1.isRefreshing = false
                }

            } catch (ex: java.lang.Exception) {
            }
        }

        nsv1 = findViewById<View>(R.id.nsv1) as NestedScrollView
        nsv1.viewTreeObserver.addOnScrollChangedListener {

            if (nsv1 != null) {
                if (nsv1.scrollY > 0) {
                    fb1.visibility = View.VISIBLE
                } else {
                    fb1.visibility = View.INVISIBLE
                }
            }
        }

        fb1 = findViewById<View>(R.id.fb1) as FloatingActionButton
        fb1.visibility = View.INVISIBLE
        fb1.setOnClickListener {
            scrollTop()
        }

        rv1 = findViewById<View>(R.id.rv1) as RecyclerView
        manager = LinearLayoutManager(this)
        rv1.layoutManager = manager

        adapter = BaseRecyclerAdapter(this)
        rv1.adapter = adapter

        rv1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (swl1.isRefreshing) {
                    return
                }

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    requestSearchPaging()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            list!!.clear()
            StopThread()
        } catch (ex: java.lang.Exception) {
        }
    }

    fun scrollTop() {
        try {
            nsv1.scrollTo(0, 0)
        } catch (ex: Exception) {
        }
    }

    private fun initThread() {

        try {

            thread = HandlerThread("Main6Activity_paging")
            thread.start()
            handler = object : Handler(thread.looper) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    when (msg.what) {
                        RESULT_PASING_SEARCH -> requestSearchPaging()
                        RESULT_PASING_SEARCHEND -> resultEndPaging()
                        else -> {}
                    }
                }
            }

        } catch (ex: java.lang.Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun StopThread() {

        try {
            if (thread != null) {
                thread.looper.quit()
                thread.quit()
            }
        } catch (ex: java.lang.Exception) {
        } finally {
            handler.removeCallbacksAndMessages(null)
            thread.interrupt()
        }
    }

    //조회
    private fun requestSearchPaging() {

        try {

            handler.post(Runnable {

                //데이터 조회시 프로그래스 보임
                runOnUiThread {
                    progressbar1.visibility = View.VISIBLE
                }

                ++ nIndex

                val sUrl = String.format("%s&q=%s&page=%d", ConstantsUrl.sUrl3, sSearchkey, nIndex)
                //Log.e(tag, sUrl)

                val sResult = GetDataString(sUrl)
                //Log.e(tag, sResult)

                //데이터 조회 완료시 프로그래스 숨김
                runOnUiThread {
                    progressbar1.visibility = View.GONE
                }

                //Log.e(tag, "데이터 조회 종료")
                try {

                    if (nTotalPage > -1) {
                        if (nIndex > nTotalPage) {
                            handler.sendEmptyMessage(RESULT_PASING_SEARCHEND)
                            return@Runnable
                        }
                    }

                } catch (ex: java.lang.Exception) {
                }

                //Log.e(tag, "데이터 조회")
                try {

                    if (!TextUtils.isEmpty(sResult)) {

                        val jsonObject = JSONObject(sResult)

                        nTotalCount = jsonObject.getInt("total_count")
                        nTotalPage = nTotalCount / nPageSize + 1

                        val jsonArray = jsonObject.getJSONArray("items")
                        for (i in 0 until jsonArray.length()) {

                            if (list == null) {
                                list = ArrayList()
                            } else {
                                list!!.clear()
                            }

                            val JSonObj = jsonArray.getJSONObject(i)

                            var ID: String? = ""
                            if (JSonObj.has("id")) {
                                ID = JSonObj.getString("id")
                            }

                            var NAME: String? = ""
                            if (JSonObj.has("name")) {
                                NAME = JSonObj.getString("name")
                            }

                            var CreateData: String? = ""
                            if (JSonObj.has("created_at")) {
                                CreateData = JSonObj.getString("created_at")
                            }

                            var HTMLURL = ""
                            if (JSonObj.has("owner")) {
                                val sOwner = JSonObj.getString("owner")
                                val jsonObj = JSONObject(sOwner)
                                if (jsonObj.has("html_url")) {
                                    HTMLURL = jsonObj.getString("html_url")
                                }
                            }

                            val item = itemData()
                            item.setItem(ID, NAME, HTMLURL, CreateData)
                            list!!.add(item)
                        }
                    }

                } catch (ex: java.lang.Exception) {
                }

                runOnUiThread {
                    try {

                        if (nTotalCount == 0) {

                            //Log.e(tag, "데이터 조회 0 인 경우")
                            handler.sendEmptyMessage(RESULT_PASING_SEARCHEND)

                        } else {

                            //Log.e(tag, "조회 데이터 추가")
                            adapter.addAll(list!!)
                        }
                    } catch (ex: java.lang.Exception) {
                    }
                }
            })

        } catch (ex: java.lang.Exception) {
        }
    }

    //조회 완료
    private fun resultEndPaging() {

        try {

            runOnUiThread {
                nTotalPage = -1 // 총 페이지
                nTotalCount = 0 // 데이터 개수
                nIndex = 0
                progressbar1.visibility = View.GONE
            }

        } catch (ex: java.lang.Exception) {
        }
    }
}