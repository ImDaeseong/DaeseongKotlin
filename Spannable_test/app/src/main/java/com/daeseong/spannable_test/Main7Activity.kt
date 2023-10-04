package com.daeseong.spannable_test

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main7Activity : AppCompatActivity() {

    private val tag = Main7Activity::class.java.simpleName

    private lateinit var et1: EditText
    private lateinit var tv1: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button

    private var sEdit: String? = null

    private var linkmap: HashMap<String, String>? = null

    private val sData = """서울          서울         서울
구름많음
온도 1.6°
미세 좋음
초미세 좋음
[https://weather.naver.com/today/01110580?cpName=KMA] 링크 처리 1 

 춘천         춘천 
<!--구름많음-->
   링크 처리 2 [https://weather.naver.com/today/01150101?cpName=KMA]

강릉__강릉
<!--흐림-->
  온도 5.6° [https://weather.naver.com/today/16113114?cpName=KMA]   링크 처리 3

!청주       청주!
초미세 좋음 [https://weather.naver.com/today/07170630?cpName=KMA] 링크 처리 4  <!--흐림-->

@대전                            대전#
미세 보통
초미세 보통
링크 처리 5 [https://weather.naver.com/today/06110517?cpName=KMA]   링크 처리 5
"""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        et1 = findViewById(R.id.et1)
        tv1 = findViewById(R.id.tv1)

        // textview scroll 추가
        tv1.movementMethod = ScrollingMovementMethod()

        et1.setText(sData)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            tv1.text = ""
            sEdit = et1.text.toString()
            readLink(sEdit!!)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            tv1.text = ""
            sEdit = et1.text.toString()
            val slink = checkLink(sEdit!!)
            setTextViewLink(slink!!)
        }
    }

    //첫번째
    private fun readLink(sInput: String) {
        var slink1: String
        var slink2: String
        var slink3: String?
        var slink4: String
        var sHtml: String
        var sTotal = ""

        var sIndex: String
        var nIndex = 0

        val sRead: Array<String?> = sInput.split("\n".toRegex()).toTypedArray()
        for (i in sRead.indices) {
            if (sRead[i] != null) {
                val sCheck = sRead[i]
                if (sCheck!!.indexOf("[") >= 0 && sCheck.indexOf("]") >= 0) {
                    slink1 = sCheck.substring(0, sCheck.indexOf("["))
                    slink2 = sCheck.substring(sCheck.indexOf("[") + 1, sCheck.indexOf("]"))
                    slink3 = sCheck.substring(sCheck.indexOf("]") + 1)
                    nIndex++
                    sIndex = String.format("Index%d", nIndex)
                    sHtml = "<a href='$slink2'><font color='#66ccff'>$sIndex</font></a>"
                    sTotal += "$slink1$sHtml$slink3<br>"
                } else {
                    slink4 = sCheck
                    sTotal += "$slink4<br>"
                }
            }
        }
        tv1.text = Html.fromHtml(sTotal)
        tv1.movementMethod = LinkMovementMethod.getInstance()
    }

    //두번째
    private fun setTextViewLink(sInput: String) {
        var slink1: String
        var slink2: String?
        var slink3: String?
        var slink4: String
        var slinkName: String
        var sHtml: String
        var sTotal = ""

        var sIndex: String
        var nIndex = 0

        val sRead: Array<String?> = sInput.split("\n".toRegex()).toTypedArray()
        for (i in sRead.indices) {
            if (sRead[i] != null) {
                val sCheck = sRead[i]
                if (sCheck!!.contains("Index")) {
                    nIndex++
                    sIndex = String.format("Index%d", nIndex)
                    slink1 = sCheck.substring(0, sCheck.indexOf(sIndex))
                    slink2 = linkmap!![sIndex]
                    slink3 = sCheck.substring(sCheck.lastIndexOf(sIndex) + 6)
                    slinkName = getNameURL(slink2!!)!!
                    sHtml = "<a href='$slink2'><font color='#66ccff'>$slinkName</font></a>"
                    sTotal += "$slink1$sHtml$slink3<br>"
                } else {
                    slink4 = sCheck
                    sTotal += "$slink4<br>"
                }
            }
        }
        tv1.text = Html.fromHtml(sTotal)
        tv1.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getNameURL(sInput: String): String? {
        val nStart = sInput.lastIndexOf("/") + 1
        var nEnd = sInput.lastIndexOf("?")
        if (nEnd < 0) {
            nEnd = sInput.length
        }
        return sInput.substring(nStart, nEnd)
    }

    private fun removeTags(sInput: String): String {
        val nStart = sInput.indexOf("[")
        val nEnd = sInput.indexOf("]")

        return if (nStart >= 0 && nEnd > nStart) {
            sInput.substring(nStart + 1, nEnd)
        } else {
            sInput
        }
    }

    private fun checkLink(sInput: String): String? {
        var slink1: String
        var slink2: String
        var slink3: String?
        var slink4: String
        var sTotal = ""
        var sIndex: String
        var nIndex = 0

        if (linkmap == null) {
            linkmap = HashMap()
        } else {
            linkmap!!.clear()
        }

        val sRead: Array<String?> = sInput.split("\n".toRegex()).toTypedArray()
        for (i in sRead.indices) {
            if (sRead[i] != null) {
                val sCheck = sRead[i]
                if (sCheck!!.indexOf("[") >= 0 && sCheck.indexOf("]") >= 0) {
                    slink1 = sCheck.substring(0, sCheck.indexOf("["))
                    slink2 = sCheck.substring(sCheck.indexOf("[") + 1, sCheck.indexOf("]"))
                    slink3 = sCheck.substring(sCheck.indexOf("]") + 1, sCheck.length)
                    nIndex++
                    sIndex = String.format("Index%d", nIndex)
                    linkmap!![sIndex] = slink2
                    sTotal = "$sTotal$slink1$sIndex$slink3\n"
                } else {
                    slink4 = sCheck
                    sTotal = "$sTotal$slink4\n"
                }
            }
        }
        return sTotal
    }
}