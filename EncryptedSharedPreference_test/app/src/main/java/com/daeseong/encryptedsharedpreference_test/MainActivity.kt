package com.daeseong.encryptedsharedpreference_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import com.daeseong.encryptedsharedpreference_test.util.encryptedsharedpreference_util

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private val REQUEST_DATA = 1
    private val RESULT_OK = 0

    private var editText1: EditText? = null
    private var editText2:EditText? = null
    private var editText3:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById<EditText>(R.id.editText1)
        editText2 = findViewById<EditText>(R.id.editText2)
        editText3 = findViewById<EditText>(R.id.editText3)

        try {

            //조회
            val sLoadID = encryptedsharedpreference_util.getInstance(this).getValue("sID", "") as String
            val sLoadPassword = encryptedsharedpreference_util.getInstance(this).getValue("sPassword", "") as String
            val sLoadMemberNumber = encryptedsharedpreference_util.getInstance(this).getValue("sMemberNumber", 1) as Int
            val sLoadSaved = encryptedsharedpreference_util.getInstance(this).getValue("sSaved", false) as Boolean
            val sLoadtemp = encryptedsharedpreference_util.getInstance(this).getValue("stemp", "") as String

            //Log.d(tag, "sLoadID:$sLoadID")
            //Log.d(tag, "sLoadPassword:$sLoadPassword")
            //Log.d(tag, "sLoadMemberNumber:$sLoadMemberNumber")
            //Log.d(tag, "sLoadSaved:$sLoadSaved")
            //Log.d(tag, "sLoadtemp:$sLoadtemp")

            editText1!!.setText(sLoadID)
            editText2!!.setText(sLoadPassword)
            editText3!!.setText(sLoadMemberNumber.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_DATA) {

            if(resultCode == RESULT_OK) {

                val sID = data!!.getStringExtra("sID")
                val sPassword = data!!.getStringExtra("sPassword")
                val sMemberNumber = data!!.getStringExtra("sMemberNumber")

                //Log.d(tag, "sID:$sID")
                //Log.d(tag, "sPassword:$sPassword")
                //Log.d(tag, "sMemberNumber:$sMemberNumber")

                editText1!!.setText(sID)
                editText2!!.setText(sPassword)
                editText3!!.setText(sMemberNumber)

                encryptedsharedpreference_util.getInstance(this).setValue("sID", sID!!)
                encryptedsharedpreference_util.getInstance(this).setValue("sPassword", sPassword!!)
                encryptedsharedpreference_util.getInstance(this).setValue("sMemberNumber", sMemberNumber!!.toInt())

            }
        }
    }

    fun btn_Save(v: View?) {

        val sID = editText1!!.text.toString()
        val sPassword = editText2!!.text.toString()
        val sMemberNumber = editText3!!.text.toString()

        if (TextUtils.isEmpty(sID)) {
            //Log.d(tag, "sID=null")
            return
        }
        if (TextUtils.isEmpty(sPassword)) {
            //Log.d(tag, "sPassword=null")
            return
        }
        if (TextUtils.isEmpty(sMemberNumber)) {
            //Log.d(tag, "sMemberNumber=null")
            return
        }

        //저장
        encryptedsharedpreference_util.getInstance(this).setValue("sID", sID)
        encryptedsharedpreference_util.getInstance(this).setValue("sPassword", sPassword)
        encryptedsharedpreference_util.getInstance(this).setValue("sMemberNumber", sMemberNumber.toInt())
        encryptedsharedpreference_util.getInstance(this).setValue("sSaved", true)
        encryptedsharedpreference_util.getInstance(this).setValue("stemp", "")
        editText1!!.setText("")
        editText2!!.setText("")
        editText3!!.setText("")
        editText1!!.requestFocus()
    }

    fun btn_Clear(v: View?) {

        //전체 해제
        encryptedsharedpreference_util.getInstance(this).clear()
        editText1!!.setText("")
        editText2!!.setText("")
        editText3!!.setText("")
        editText1!!.requestFocus()
    }

    fun btn_Remove(v: View?) {

        //전체 삭제
        val alls: Map<String?, *>? = encryptedsharedpreference_util.getInstance(this).getAll()
        for (sKey in alls!!.keys) {
            Log.d(tag, "sKey:$sKey")
            encryptedsharedpreference_util.getInstance(this).remove(sKey)
        }
        editText1!!.setText("")
        editText2!!.setText("")
        editText3!!.setText("")
        editText1!!.requestFocus()
    }

    fun btn_Get(v: View?) {

        /*
        //단순 Activity 호출
        val intent = Intent(this, SharedPreferencesActivity::class.java)
        startActivity(intent)
        */

        //Activity 호출후 결과값 받음
        val sLoadID = encryptedsharedpreference_util.getInstance(this).getValue("sID", "") as String
        val sLoadPassword = encryptedsharedpreference_util.getInstance(this).getValue("sPassword", "") as String
        val sLoadMemberNumber = encryptedsharedpreference_util.getInstance(this).getValue("sMemberNumber", 1) as Int
        val sLoadSaved = encryptedsharedpreference_util.getInstance(this).getValue("sSaved", false) as Boolean
        val sLoadtemp = encryptedsharedpreference_util.getInstance(this).getValue("stemp", "") as String

        if (TextUtils.isEmpty(sLoadID)) {
            Log.d(tag, "sID=null")
            return
        }

        if (TextUtils.isEmpty(sLoadPassword)) {
            Log.d(tag, "sPassword=null")
            return
        }

        if (TextUtils.isEmpty(sLoadMemberNumber.toString())) {
            Log.d(tag, "sMemberNumber=null")
            return
        }

        val intent = Intent(this, SharedResultActivity::class.java)
        intent.putExtra("sID", sLoadID)
        intent.putExtra("sPassword", sLoadPassword)
        intent.putExtra("sMemberNumber", sLoadMemberNumber)
        intent.putExtra("sSaved", sLoadSaved)
        intent.putExtra("stemp", sLoadtemp)
        startActivityForResult(intent, REQUEST_DATA)
    }
}