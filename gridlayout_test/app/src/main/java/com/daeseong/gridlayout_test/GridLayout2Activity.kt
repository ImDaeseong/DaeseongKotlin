package com.daeseong.gridlayout_test

import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.GridLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class GridLayout2Activity : AppCompatActivity() {

    private val tag: String = GridLayout2Activity::class.java.simpleName

    private val numRows = 20
    private val numCols = 11

    private var scrollView: ScrollView? = null
    private var gridLayout: GridLayout? = null
    private var floatingActionButton: FloatingActionButton? = null

    private var AlpabatList = arrayOf(
        "", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ",
        "ㄱ", "가", "갸", "거", "겨", "고", "교", "구", "규", "그", "기",
        "ㄴ", "나", "냐", "너", "녀", "노", "뇨", "누", "뉴", "느", "니",
        "ㄷ", "다", "댜", "더", "뎌", "도", "됴", "두", "듀", "드", "디",
        "ㄹ", "라", "랴", "러", "려", "로", "료", "루", "류", "르", "리",
        "ㅁ", "마", "먀", "머", "며", "모", "묘", "무", "뮤", "므", "미",
        "ㅂ", "바", "뱌", "버", "벼", "보", "뵤", "부", "뷰", "브", "비",
        "ㅅ", "사", "샤", "서", "셔", "소", "쇼", "수", "슈", "스", "시",
        "ㅇ", "아", "야", "어", "여", "오", "요", "우", "유", "으", "이",
        "ㅈ", "자", "쟈", "저", "져", "조", "죠", "주", "쥬", "즈", "지",
        "ㅊ", "차", "챠", "처", "쳐", "초", "쵸", "추", "츄", "츠", "치",
        "ㅋ", "카", "캬", "커", "켜", "코", "쿄", "쿠", "큐", "크", "키",
        "ㅌ", "타", "탸", "터", "텨", "토", "툐", "투", "튜", "트", "티",
        "ㅍ", "파", "퍄", "퍼", "펴", "포", "표", "푸", "퓨", "프", "피",
        "ㅎ", "하", "햐", "허", "혀", "호", "효", "후", "휴", "흐", "히",
        "ㄲ", "까", "꺄", "꺼", "껴", "꼬", "꾜", "꾸", "뀨", "끄", "끼",
        "ㄸ", "따", "땨", "떠", "뗘", "또", "뚀", "뚜", "뜌", "뜨", "띠",
        "ㅃ", "빠", "뺘", "뻐", "뼈", "뽀", "뾰", "뿌", "쀼", "쁘", "삐",
        "ㅆ", "싸", "쌰", "써", "쎠", "쏘", "쑈", "쑤", "쓔", "쓰", "씨",
        "ㅉ", "짜", "쨔", "쩌", "쪄", "쪼", "쬬", "쭈", "쮸", "쯔", "찌"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout2)

        scrollView = findViewById<ScrollView>(R.id.sv1)
        gridLayout  = findViewById<GridLayout>(R.id.gl1)

        floatingActionButton = findViewById<FloatingActionButton>(R.id.fab1)
        floatingActionButton!!.setOnClickListener {
            scrollView!!.scrollTo(0, 0)
        }

        scrollView!!.viewTreeObserver.addOnScrollChangedListener {
            if (scrollView!!.scrollY > 0) {
                floatingActionButton!!.visibility = View.VISIBLE
            } else {
                floatingActionButton!!.visibility = View.INVISIBLE
            }
        }

        loadData()
    }

    private fun loadData() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val screenWidth: Int = size.x
        val screenHeight: Int = size.y
        gridLayout!!.columnCount = 11
        gridLayout!!.rowCount = 20
        for (element in AlpabatList) {
            val item = GridItem(this)
            item.setText(element)
            item.setOnClickListener { view ->
                val sText = (view as GridItem).getText()
                if (!TextUtils.isEmpty(sText)) {
                    Toast.makeText(this@GridLayout2Activity, sText, Toast.LENGTH_SHORT).show()
                }
            }
            gridLayout!!.addView(item, screenWidth / numCols, 60)
        }
    }
}
