package com.daeseong.stringutilslib

import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Stringutils {

    // /,? 사이의 문자열 반환
    fun getSubStringUrl(sUrl: String): String {
        var sResult = ""
        try {
            val start = sUrl.lastIndexOf("/") + 1
            var end = sUrl.lastIndexOf("?")
            if (end < 0) {
                end = sUrl.length
            }
            sResult = sUrl.substring(start, end)
        } catch (e: NullPointerException) {
        }
        return sResult
    }

    // /뒤에 문자열 반환
    fun getlastStringUrl(sUrl: String): String {
        var sResult = ""
        try {
            sResult = sUrl.substring(sUrl.lastIndexOf("/") + 1)
        } catch (e: NullPointerException) {
        }
        return sResult
    }

    //확장자 반환
    fun getStringExt(sUrl: String): String {
        var sResult = ""
        try {
            sResult = sUrl.substring(sUrl.lastIndexOf(".") + 1)
        } catch (e: NullPointerException) {
        }
        return sResult
    }

    //대문자
    fun getStringUpper(sInput: String?): String {
        val stringBuffer = StringBuffer()
        if (sInput != null) {
            for (element in sInput) {
                val ch = element
                if (Character.isLowerCase(ch)) {
                    stringBuffer.append(Character.toUpperCase(ch))
                } else {
                    stringBuffer.append(ch)
                }
            }
        }
        return stringBuffer.toString()
    }

    //소문자
    fun getStringLower(sInput: String?): String {
        val stringBuffer = StringBuffer()
        if (sInput != null) {
            for (element in sInput) {
                val ch = element
                if (Character.isUpperCase(ch)) {
                    stringBuffer.append(Character.toLowerCase(ch))
                } else {
                    stringBuffer.append(ch)
                }
            }
        }
        return stringBuffer.toString()
    }

    //문자형 숮자
    fun getStringDecimalFormat(sInput: String): String {
        val value: Double = try {
            sInput.toDouble()
        } catch (e: Exception) {
            return ""
        }
        val df = DecimalFormat("#,###.##")
        return df.format(value)
    }

    //숫자형 문자
    fun getDoubleCovnertToString(dInput: Double?): String {
        val value: String = try {
            val df = DecimalFormat("#,###.##")
            df.format(dInput)
        } catch (e: Exception) {
            return "0"
        }
        return value
    }

    //시간 표시
    fun getStringTime(nformat: Int): String {
        var format = ""

        when(nformat){
            1 -> format = "yyyy-MM-dd hh:mm:ss"
            2 -> format = "yyyyMMddhhmmssSSS"
            3 -> format = "yyyy-MM-dd"
            4 -> format = "yyyy년MM월dd일HH시mm분ss초"
            5 -> format = "yyyy.MM.dd HH시mm분"
        }

        val sDateFormat = SimpleDateFormat(format)
        return sDateFormat.format(Date())
    }

    //string convert Date
    fun getStringToDate(sInput: String?, nformat: Int): Date {
        var format = ""

        when(nformat){
            1 -> format = "yyyy-MM-dd hh:mm:ss"
            2 -> format = "yyyyMMddhhmmssSSS"
            3 -> format = "yyyy-MM-dd"
            4 -> format = "yyyy년MM월dd일HH시mm분ss초"
            5 -> format = "yyyy.MM.dd HH시mm분"
        }

        val sDateFormat = SimpleDateFormat(format)
        var convertedDate = Date()
        try {
            convertedDate = sDateFormat.parse(sInput)
        } catch (e: ParseException) {
        }
        return convertedDate
    }

    //Date convert string
    fun getDateToString(dDate: Date?, nformat: Int): String {
        val value: String
        var format = ""

        when(nformat){
            1 -> format = "yyyy-MM-dd hh:mm:ss"
            2 -> format = "yyyyMMddhhmmssSSS"
            3 -> format = "yyyy-MM-dd"
            4 -> format = "yyyy년MM월dd일HH시mm분ss초"
            5 -> format = "yyyy.MM.dd HH시mm분"
        }

        val sDateFormat = SimpleDateFormat(format)
        value = sDateFormat.format(dDate)
        return value
    }

    //쌈따옴표를 제거
    fun removeStringQuoted(sInput: String): String {
        val result = sInput.trim { it <= ' ' }.replace("^\"|\"$".toRegex(), "")
        return result.trim { it <= ' ' }
    }

    //문자열 분리
    fun splitString(sInput: String?, delim: String?): Array<String?> {
        val token = StringTokenizer(sInput, delim)
        val result = arrayOfNulls<String>(token.countTokens())
        var i = 0
        while (token.hasMoreTokens()) {
            result[i] = token.nextToken()
            i++
        }
        return result
    }

    //전화번호 정규식
    fun isPhone(sInput: String): Boolean {
        val regex = Regex("\\d{2,4}-\\d{3,4}-\\d{4}$")
        return sInput.matches(regex)
    }

    //이메일 정규식
    fun isEmail(sInput: String): Boolean {
        val regex =  Regex("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")
        return sInput.matches(regex)
    }

    //숫자만 정규식
    fun isNumeric(sInput: String): Boolean {
        val regex = Regex("[\\d]*$")
        return sInput.matches(regex)
    }
}

