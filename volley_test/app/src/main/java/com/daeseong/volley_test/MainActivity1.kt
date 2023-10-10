package com.daeseong.volley_test

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*

class MainActivity1 : AppCompatActivity() {

    private val TAG = MainActivity1::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView

    private var requestQueue: RequestQueue? = null

    //호출 정보
    private val sPage = "http://www.worldjob.or.kr/openapi/openapi.do?" ////https://www.data.go.kr/dataset/3038249/openapi.do
    private val sdobType = "1" //1:해외취업,2:해외연수,3:해외인턴,4:해외봉사,5:해외창업
    private val sdsptcKsco = "01" //직종별코드(해외취업,연수만 사용)01:전산,컴퓨터,02:전기/전자,06:기계/금속,07:건설/토목,08:사무/서비스,09:의료,10:기타
    private val scontinent = "1" //대륙별코드 1:아시아,2:북아메리카, 3:남아메리카,4:유럽,5:오세아니아,6:아프리카
    private val sepmt61 = "Y" //일자리Best20(해외취업만 사용)Y,N
    private val sshowItemListCount = "1000" //한번에보여질리스트갯수출력결과

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        initVolley()

        tv1 = findViewById(R.id.tv1)
        tv1.movementMethod = ScrollingMovementMethod()

        tv2 = findViewById(R.id.tv2)
        tv2.movementMethod = ScrollingMovementMethod()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            // GET
            requestQueue?.add(requestGetData())

            // POST
            requestQueue?.add(requestPOSTData())
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            requestQueue?.cancelAll(this)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private fun initVolley() {
        requestQueue = Volley.newRequestQueue(this)
    }

    private fun requestGetData(): StringRequest {
        val sUrl = String.format("%sdobType=%s&dsptcKsco=%s&continent=%s&showItemListCount=%s&sepmt61=%s", sPage, sdobType, sdsptcKsco, scontinent, sshowItemListCount, sepmt61)
        val sr = StringRequest(Request.Method.GET, sUrl,
            { response ->

                tv1.text = response
                val nTop = tv1.layout.getLineTop(tv1.lineCount)
                val nScrollY = nTop - tv1.height
                if (nScrollY > 0) {
                    tv1.scrollTo(0, nScrollY)
                } else {
                    tv1.scrollTo(0, 0)
                }
            }
        ) { error ->
            Log.e(TAG,"requestGetData onErrorResponse: " + error.message.toString())
        }
        sr.retryPolicy = DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        sr.setShouldCache(false)
        return sr
    }

    private fun requestPOSTData(): StringRequest {

        val sUrl = sPage
        val sr: StringRequest = object : StringRequest(Method.POST, sUrl,
            Response.Listener { response ->

                tv2.text = response
                val nTop = tv2.layout.getLineTop(tv2.lineCount)
                val nScrollY = nTop - tv2.height
                if (nScrollY > 0) {
                    tv2.scrollTo(0, nScrollY)
                } else {
                    tv2.scrollTo(0, 0)
                }
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "requestPOSTData onErrorResponse: " + error.message.toString())
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val param: MutableMap<String, String> = HashMap()
                param["dobType"] = sdobType
                param["dsptcKsco"] = sdsptcKsco
                param["continent"] = scontinent
                param["showItemListCount"] = sshowItemListCount
                param["sepmt61"] = sepmt61
                return param
            }
        }
        sr.retryPolicy = DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        sr.setShouldCache(false)
        return sr
    }
}