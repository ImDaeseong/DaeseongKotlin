package com.daeseong.banner_test

import android.util.Log
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.floor

object String_util {

    private val tag: String = String_util::class.java.simpleName

    //파일 확장자
    fun getExt(url: String): String {
        var sResult = ""
        try {
            sResult = url.substring(url.lastIndexOf(".") + 1)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return sResult
    }

    //파일 이름
    fun getImageName(url: String): String {
        var sResult = ""
        try {
            sResult = url.substring(url.lastIndexOf("/") + 1)
        } catch (e: java.lang.NullPointerException) {
            e.printStackTrace()
        }
        return sResult
    }

    //대문자 변환
    fun getUpper(sInput: String?): String {
        //val sResult = sInput!!.toUpperCase()
        //return sResult
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

    //소문자 변화
    fun getLower(sInput: String?): String? {
        //val sResult = sInput!!.toLowerCase()
        //return sResult
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

    //문자형 숫자
    fun getMoneyDecimalFormat(sInput: String): String {
        val value: Double = try {
            sInput.toDouble()
        } catch (e: Exception) {
            return ""
        }
        val df = DecimalFormat("###,##0.00")
        return df.format(value)
    }

    //시간 문자형
    fun getTime(): String {
        val format1 = "yyyy-MM-dd hh:mm:ss"
        val format2 = "yyyyMMddhhmmssSSS"
        val format3 = "yyyy-MM-dd"
        val format4 = "yyyy년MM월dd일HH시mm분ss초"
        val sDateFormat = SimpleDateFormat(format4)
        return sDateFormat.format(Date())
    }

    //시간 데이터형
    fun getDate(sInput: String?, Format: String?): Date? {
        if (sInput == null) return null
        val sDateFormat = SimpleDateFormat(Format)
        var convertedDate: Date? = Date()
        try {
            convertedDate = sDateFormat.parse(sInput)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return convertedDate
    }

    fun isExpired(sInput: String?): Boolean {
        var expiring = false
        val nExpiredDay = 3
        try {
            //val startDate = getDate("2018-02-05 12:00:00", "yyyy-MM-dd hh:mm:ss")
            val startDate = getDate(sInput, "yyyy-MM-dd")
            val lTime: Long = String_util.getDifferentDays(startDate!!, Date())
            if (lTime >= nExpiredDay) {
                expiring = true
            }
            return expiring
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return expiring
    }

    //날짜 차이
    private fun getDifferentDays(startDate: Date, endData: Date): Long {
        try {
            val duration = endData.time - startDate.time
            return TimeUnit.MILLISECONDS.toDays(duration)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return 0
    }

    //시간 차이
    fun getDifferentHours(startDate: Date, endData: Date): Long {
        try {
            val duration = endData.time - startDate.time
            return TimeUnit.MILLISECONDS.toHours(duration)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return 0
    }

    //분 차이
    fun getDifferentMinutes(startDate: Date, endData: Date): Long {
        try {
            val duration = endData.time - startDate.time
            return TimeUnit.MILLISECONDS.toMinutes(duration)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return 0
    }

    //쌈따옴표를 제거
    fun removeQuoted(sInput: String): String? {
        val result = sInput.trim { it <= ' ' }.replace("^\"|\"$".toRegex(), "")
        return result.trim { it <= ' ' }
    }

    //문자열 분리
    fun split(sInput: String?, delim: String?): Array<String?>? {
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
    fun isPhoneNo(sInput: String): Boolean {

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

    //숫자 포함 여부
    fun isNumericInc(sInput: String): Boolean {
        var isNumber = false
        val regex = Regex("[\\d]*$")
        var sub = ""
        for (i in sInput.indices) {
            sub = sInput.substring(i, i + 1)
            if (!isNumber) isNumber = sub.matches(regex)
        }
        return isNumber
    }

    fun getBannerIndex(): Int {

        val nMax = 8
        val bannclip = intArrayOf(5, 0, 30, 0, 0, 45, 0, 20)
        var nVal = (Math.random() * nMax).toInt() + 1
        if (nVal == 0) nVal = 1
        var nResult = 0
        for (i in 0 until nMax) {
            if (bannclip[i] != 0) {

                // 몫
                val nMok = bannclip[i] / nVal

                // 나머지
                val nNa = bannclip[i] % nVal

                //Log.e("d", "Hotclip: " + Hotclip[i] + "몫: " +  nMok + "나머지: " +  nNa);
                if (nMok == 0 && nNa == nVal) {
                    if (nResult < bannclip[i]) nResult = bannclip[i]
                }
                if (nMok > 0 && nNa == 0) {
                    if (nResult < bannclip[i]) nResult = bannclip[i]
                }
                if (nMok > 0 && nNa < nVal) {
                    if (nResult < bannclip[i]) nResult = bannclip[i]
                }
                if (nMok > 0 && nNa > nVal) {
                    if (nResult < bannclip[i]) nResult = bannclip[i]
                }
            }
        }
        return nResult
    }

    fun isEmpty(sInput: String?): Boolean {
        return sInput == null || sInput.isEmpty()
    }

    fun getTrim(sInput: String?): String? {
        return sInput?.trim { it <= ' ' }
    }

    fun getrandomUUID(): String? {
        var sResult = ""
        sResult = try {
            UUID.randomUUID().toString().replace("-", "")
        } catch (e: java.lang.Exception) {
            return ""
        }
        return sResult
    }

    fun getReplace(sInput: String): String? {
        return sInput.replace(" ", "+")
    }

    fun getFileSize(lLength: Long): String? {
        val unit = charArrayOf('B', 'K', 'M', 'G')
        var index = 0
        var div = 1
        while (index < unit.size) {
            div *= 1024
            if (lLength < div) {
                break
            }
            index++
        }
        div /= 1024
        return if (lLength % div == 0L) {
            (lLength / div).toString() + unit[index]
        } else {
            val df = DecimalFormat("#.00")
            df.format(lLength * 1.0 / div) + unit[index]
        }
    }

    fun getLastVisitDay(sInput: String?): String? {

        var sResult = ""

        try {

            val startDate = getDate(sInput, "yyyy-MM-dd")
            val lTime = getDifferentDays(startDate!!, Date())

            //Math.floor 로 소수점 무시하고 앞에 데이터만 처리
            val nYears = floor((lTime.toFloat() / 365).toDouble()).toInt()
            val nTempYears = lTime - nYears * 365
            val nMonths = floor((nTempYears.toFloat() / 30).toDouble()).toInt()
            val nTempMonths = nTempYears - nMonths * 30
            val nWeeks = floor((nTempMonths.toFloat() / 7).toDouble()).toInt()
            val nDays = floor((nTempMonths.toFloat() - nWeeks * 7).toDouble()).toInt()

            //val sValue = String.format("[%d]  %d년 %d개월 %d주 %d일 전", lTime, nYears, nMonths, nWeeks, nDays)
            //Log.e(tag, sValue)


            if (lTime == 0L) {

                sResult = String.format("오늘")
            }

            if (lTime in 1..6) {

                sResult = String.format("%d일 전", lTime)
            }

            if (lTime in 7..29) {
                sResult = if (nDays == 0) {
                    String.format("%d주 %d일 전", nWeeks, nDays)
                } else {
                    String.format("%d주 전", nWeeks)
                }
            }

            if (lTime in 30..364) {

                sResult = if (nWeeks == 0) {
                    String.format("%d개월", nMonths)
                } else {
                    String.format("%d개월 %d주", nMonths, nWeeks)
                }

                sResult += if (nDays == 0) {
                    String.format(" 전")
                } else {
                    String.format(" %d일 전", nDays)
                }
            }

            if (lTime > 365) {

                sResult = if (nMonths == 0) {
                    String.format("%d년", nYears)
                } else {
                    String.format("%d년 %d개월", nYears, nMonths)
                }

                sResult += if (nWeeks == 0) {
                    String.format("")
                } else {
                    String.format(" %d주", nWeeks)
                }

                sResult += if (nDays == 0) {
                    String.format(" 전")
                } else {
                    String.format(" %d일 전", nDays)
                }
            }

        } catch (e: java.lang.Exception) {
        }

        return sResult
    }

}

