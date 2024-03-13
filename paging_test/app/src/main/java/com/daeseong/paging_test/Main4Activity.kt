package com.daeseong.paging_test

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.AbsListView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.daeseong.paging_test.API.SearchApi
import com.daeseong.paging_test.Common.GetStringTask
import com.daeseong.paging_test.Model.Base4Adapter
import org.json.JSONObject

class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private lateinit var swl1: SwipeRefreshLayout
    private lateinit var lv1: ListView
    private lateinit var base4Adapter: Base4Adapter

    private var sResult = ""

    private var nTotalPage = 0 // 총 페이지
    private var nTotalCount = 0 // 데이터 개수
    private val nPageSize = 10 // 페이지에 보여줄 개수

    private var sUrl = ""
    private val sSearchkey = "android"
    private var nIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        initTotalpage()

        swl1 = findViewById(R.id.swl1)
        lv1 = findViewById(R.id.lv1)

        base4Adapter = Base4Adapter()
        lv1.adapter = base4Adapter

        lv1.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(absListView: AbsListView, i: Int) {
                when (i) {
                    AbsListView.OnScrollListener.SCROLL_STATE_IDLE -> Log.e(tag, "스크롤 이동하다가 멈추었을때")
                    AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL -> Log.e(tag, "스크롤 터치되어 있을 때")
                    AbsListView.OnScrollListener.SCROLL_STATE_FLING -> Log.e(tag, "스크롤 움직이고 있을때")
                }
            }

            override fun onScroll(absListView: AbsListView, i: Int, i1: Int, i2: Int) {
                val nlastItem = lv1.lastVisiblePosition + 1
                if (nlastItem == i2) {
                    Log.e(tag, "스크롤 마지막에 도착")
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
    }

    override fun onDestroy() {
        super.onDestroy()
        SearchApi.getInstance().clear()
    }

    private fun initTotalpage() {
        try {

            sUrl = String.format("%s&q=%s", ConstantsUrl.sUrl1, sSearchkey)
            sResult = GetStringTask().execute(sUrl)!!

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
                    base4Adapter.addAll(list)
                }
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

}