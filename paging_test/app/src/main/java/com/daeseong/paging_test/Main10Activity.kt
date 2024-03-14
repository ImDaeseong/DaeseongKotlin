package com.daeseong.paging_test

import android.os.Bundle
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
import com.daeseong.paging_test.Common.HttpUtilOK
import com.daeseong.paging_test.Model.BaseRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class Main10Activity : AppCompatActivity() {

    private val tag = Main10Activity::class.simpleName

    private lateinit var swl1: SwipeRefreshLayout
    private lateinit var nsv1: NestedScrollView

    private lateinit var fb1: FloatingActionButton

    private lateinit var progressbar1: ProgressBar

    private lateinit var rv1: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: BaseRecyclerAdapter

    private var nTotalPage = -1  // 총 페이지
    private var nTotalCount = 0  // 데이터 개수
    private var nIndex = 1      // 현재 페이지
    private var nPageSize = 1   // 페이지에 보여줄 개수
    private var sSearchkey = "android"

    //paging 처리
    private var bSearchFlag = false

    private var list: MutableList<SearchApi.itemData>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)

        init()

        //시작시 페이지 조회 정보 초기화
        nTotalPage = -1
        nTotalCount = 0
        nIndex = 1
        bSearchFlag = false

        requestSearchPaging()
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            list?.clear()
            adapter.clear()
        } catch (ex: Exception) {
        }
    }

    private fun scrollTop() {
        try {
            nsv1.scrollTo(0, 0)
        } catch (ex: Exception) {
        }
    }

    private fun init() {

        //프로그래스 바 초기화
        progressbar1 = findViewById(R.id.progressbar1)
        progressbar1.visibility = View.GONE

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
                nIndex = 1
                bSearchFlag = false
                adapter.clear()

                requestSearchPaging()

                if (swl1.isRefreshing) {
                    swl1.isRefreshing = false
                }

            } catch (ex: java.lang.Exception) {
            }
        }

        //NestedScrollView
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

        //FloatingActionButton
        fb1 = findViewById<View>(R.id.fb1) as FloatingActionButton
        fb1.visibility = View.INVISIBLE
        fb1.setOnClickListener {
            scrollTop()
        }


        //RecyclerView
        rv1 = findViewById<View>(R.id.rv1) as RecyclerView
        manager = LinearLayoutManager(this)
        rv1.layoutManager = manager


        //Adapter
        adapter = BaseRecyclerAdapter(this)
        rv1.adapter = adapter


        //RecyclerView 스크롤 이벤트
        rv1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (swl1.isRefreshing) {

                    Log.e(tag, "새로 고침 중일때는 무시")
                    return
                }

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {

                    if (bSearchFlag) {
                        Log.e(tag, "검색 데이터 모두 로드 완료")
                    } else {
                        requestSearchPaging()
                    }
                }
            }
        })
    }

    //조회 - HttpUtilOK.getData 사용
    private fun requestSearchPaging() {

        try {

            // 데이터 조회시 프로그래스 보임
            runOnUiThread {
                progressbar1.visibility = View.VISIBLE
            }

            val sUrl = String.format("%s&q=%s&page=%d", ConstantsUrl.sUrl2, sSearchkey, nIndex)
            Log.e(tag, sUrl)

            // OkHttp를 사용하여 데이터 요청
            HttpUtilOK.getData(sUrl, object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                    runOnUiThread {
                        progressbar1.visibility = View.GONE
                    }
                }

                override fun onResponse(call: Call, response: Response) {

                    val sResult = response.body?.string()

                    // 데이터 조회 완료시 프로그래스 숨김
                    runOnUiThread {
                        progressbar1.visibility = View.GONE
                    }

                    try {

                        if (nTotalPage > -1 && nIndex > nTotalPage) {
                            resultEndPaging()
                            return
                        }

                        if (!sResult.isNullOrEmpty()) {

                            val jsonObject = JSONObject(sResult)

                            if (nTotalCount == 0) {
                                nTotalCount = jsonObject.getInt("total_count")
                                nTotalPage = nTotalCount / nPageSize + 1
                            }
                            //Log.e(tag, "nTotalCount:$nTotalCount")
                            //Log.e(tag, "nTotalPage:$nTotalPage")

                            if (list == null) {
                                list = ArrayList()
                            } else {
                                list!!.clear()
                            }

                            val jsonArray = jsonObject.getJSONArray("items")
                            for (i in 0 until jsonArray.length()) {
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

                                val item = SearchApi.itemData()
                                item.setItem(ID, NAME, HTMLURL, CreateData)
                                list!!.add(item)
                            }
                        }

                        if (nTotalCount == 0) {

                            Log.e(tag, "데이터 조회 0 인 경우")
                            resultEndPaging()
                        } else {

                            Log.e(tag, "조회 데이터 추가")
                            runOnUiThread {
                                adapter.addAll(list!!)
                            }
                        }

                    } catch (ex: Exception) {
                        Log.e(tag, "Exception:" + ex.message.toString())
                    }
                }
            })

            // HTTP 요청이 성공한 경우에만 nIndex 증가
            ++nIndex
            //Log.e(tag, "nIndex:$nIndex")

        } catch (ex: Exception) {

            runOnUiThread {
                progressbar1.visibility = View.GONE
            }
        }
    }

    //조회 완료
    private fun resultEndPaging() {

        try {

            nTotalPage = -1 // 총 페이지
            nTotalCount = 0 // 데이터 개수
            nIndex = 1
            bSearchFlag = true

            runOnUiThread {
                progressbar1.visibility = View.GONE
            }

        } catch (ex: java.lang.Exception) {
        }
    }
}