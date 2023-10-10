package com.daeseong.library_test

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.stringutilslib.Stringutils.getDateToString
import com.daeseong.stringutilslib.Stringutils.getDoubleCovnertToString
import com.daeseong.stringutilslib.Stringutils.getStringDecimalFormat
import com.daeseong.stringutilslib.Stringutils.getStringExt
import com.daeseong.stringutilslib.Stringutils.getStringLower
import com.daeseong.stringutilslib.Stringutils.getStringTime
import com.daeseong.stringutilslib.Stringutils.getStringToDate
import com.daeseong.stringutilslib.Stringutils.getStringUpper
import com.daeseong.stringutilslib.Stringutils.getSubStringUrl
import com.daeseong.stringutilslib.Stringutils.getlastStringUrl
import com.daeseong.stringutilslib.Stringutils.isEmail
import com.daeseong.stringutilslib.Stringutils.isNumeric
import com.daeseong.stringutilslib.Stringutils.isPhone
import com.daeseong.stringutilslib.Stringutils.removeStringQuoted
import com.daeseong.stringutilslib.Stringutils.splitString
import java.util.*

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button10: Button
    private lateinit var button11: Button
    private lateinit var button12: Button
    private lateinit var button13: Button
    private lateinit var button14: Button

    private var sInput: String? = null
    private var dInput = 0.0
    private var nType = 0
    private var dDate: Date? = null
    private var sResult: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            sInput = "https://daeseong.com/board/mainfolder?index=1"
            sResult = getlastStringUrl(sInput!!)
            Log.e(tag, "getlastStringUrl:$sResult")
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            sInput = "https://daeseong.com/board/mainfolder?index=1"
            sResult = getSubStringUrl(sInput!!)
            Log.e(tag, "getFileNameURL:$sResult")
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            sInput = "https://daeseong.com/board/mainfolder?index=test.exe"
            sResult = getStringExt(sInput!!)
            Log.e(tag, "getStringExt:$sResult")
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            sInput = "https://daeseong.com/board/mainfolder?index=1"
            sResult = getStringUpper(sInput)
            Log.e(tag, "getStringUpper:$sResult")
        }

        button5 = findViewById(R.id.button5)
        button5.setOnClickListener {
            sInput = "https://daeseong.com/board/mainfolder?index=test.exe"
            sResult = getStringLower(sInput)
            Log.e(tag, "getStringLower:$sResult")
        }

        button6 = findViewById(R.id.button6)
        button6.setOnClickListener {
            sInput = "3154055"
            sResult = getStringDecimalFormat(sInput!!)
            Log.e(tag, "getStringDecimalFormat:$sResult")
        }

        button7 = findViewById(R.id.button7)
        button7.setOnClickListener {
            dInput = 3154055.0
            sResult = getDoubleCovnertToString(dInput)
            Log.e(tag, "getDoubleCovnertToString:$sResult")
        }

        button8 = findViewById(R.id.button8)
        button8.setOnClickListener {

            nType = 1
            sResult = getStringTime(nType)
            Log.e(tag, "getStringTime:$sResult")

            nType = 2
            sResult = getStringTime(nType)
            Log.e(tag, "getStringTime:$sResult")

            nType = 3
            sResult = getStringTime(nType)
            Log.e(tag, "getStringTime:$sResult")

            nType = 4
            sResult = getStringTime(nType)
            Log.e(tag, "getStringTime:$sResult")

            nType = 5
            sResult = getStringTime(nType)
            Log.e(tag, "getStringTime:$sResult")
        }

        button9 = findViewById(R.id.button9)
        button9.setOnClickListener {

            nType = 3
            sResult = getStringTime(nType)
            Log.e(tag, "getStringTime:$sResult")

            dDate = getStringToDate(sResult, nType)
            Log.e(tag, "getStringToDate:" + dDate.toString())

            sResult = getDateToString(dDate, nType)
            Log.e(tag, "getDateToString:$sResult")
        }

        button10 = findViewById(R.id.button10)
        button10.setOnClickListener {
            sInput = "\"test\""
            sResult = removeStringQuoted(sInput!!)
            Log.e(tag, "removeStringQuoted:$sResult")
        }

        button11 = findViewById(R.id.button11)
        button11.setOnClickListener {
            sInput = "test1,test2,test3"
            val sArray = splitString(sInput, ",")
            for (i in sArray.indices) {
                Log.e(tag, "splitString:" + sArray[i])
            }
        }

        button12 = findViewById(R.id.button12)
        button12.setOnClickListener {
            sInput = "010-1100-7001"
            if (isPhone(sInput!!)) {
                Log.e(tag, "isPhone number")
            } else {
                Log.e(tag, "Not isPhone number")
            }
        }

        button13 = findViewById(R.id.button13)
        button13.setOnClickListener {
            sInput = "test@gmail.com"
            if (isEmail(sInput!!)) {
                Log.e(tag, "isEmail number")
            } else {
                Log.e(tag, "Not isEmail number")
            }
        }

        button14 = findViewById(R.id.button14)
        button14.setOnClickListener {

            sInput = "01011007001"
            if (isNumeric(sInput!!)) {
                Log.e(tag, "isNumeric number")
            } else {
                Log.e(tag, "Not isNumeric number")
            }
        }
    }
}