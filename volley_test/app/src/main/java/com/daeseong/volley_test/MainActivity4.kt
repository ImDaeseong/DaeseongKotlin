package com.daeseong.volley_test

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity4 : AppCompatActivity() {

    private val TAG = MainActivity4::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView

    //호출 정보
    private val sPage = "http://www.worldjob.or.kr/openapi/openapi.do?" ////https://www.data.go.kr/dataset/3038249/openapi.do
    private val sdobType = "1" //1:해외취업,2:해외연수,3:해외인턴,4:해외봉사,5:해외창업
    private val sdsptcKsco = "01" //직종별코드(해외취업,연수만 사용)01:전산,컴퓨터,02:전기/전자,06:기계/금속,07:건설/토목,08:사무/서비스,09:의료,10:기타
    private val scontinent = "1" //대륙별코드 1:아시아,2:북아메리카, 3:남아메리카,4:유럽,5:오세아니아,6:아프리카
    private val sepmt61 = "Y" //일자리Best20(해외취업만 사용)Y,N
    private val sshowItemListCount = "1000" //한번에보여질리스트갯수출력결과

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        GetRequestData.getInstance().init(this)

        tv1 = findViewById(R.id.tv1)
        tv1.movementMethod = ScrollingMovementMethod()

        tv2 = findViewById(R.id.tv2)
        tv2.movementMethod = ScrollingMovementMethod()

        button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {

            tv1!!.text = ""
            val sUrl = String.format("%sdobType=%s&dsptcKsco=%s&continent=%s&showItemListCount=%s&sepmt61=%s", sPage, sdobType, sdsptcKsco, scontinent, sshowItemListCount, sepmt61)
            GetRequestData.getInstance().getData(sUrl,
                { response ->

                    tv1.text = response
                    val nTop = tv1.layout.getLineTop(tv1.lineCount)
                    val nScrollY = nTop - tv1.height
                    if (nScrollY > 0) {
                        tv1.scrollTo(0, nScrollY)
                    } else {
                        tv1.scrollTo(0, 0)
                    }
                }, { error ->
                    Log.e(TAG,"getData onErrorResponse: " + error.message.toString())
                })
        }

        button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {

            tv2!!.text = ""
            val sUrl = sPage
            GetRequestData.getInstance().getPostData(sUrl,
                { response ->

                    tv2.text = response
                    val nTop = tv2.layout.getLineTop(tv2.lineCount)
                    val nScrollY = nTop - tv2.height
                    if (nScrollY > 0) {
                        tv2.scrollTo(0, nScrollY)
                    } else {
                        tv2.scrollTo(0, 0)
                    }
                },
                { error ->
                    Log.e(TAG,"getPostData onErrorResponse: " + error.message.toString())
                }, sdobType, sdsptcKsco, scontinent, sshowItemListCount, sepmt61
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        GetRequestData.getInstance().clear()
    }
}