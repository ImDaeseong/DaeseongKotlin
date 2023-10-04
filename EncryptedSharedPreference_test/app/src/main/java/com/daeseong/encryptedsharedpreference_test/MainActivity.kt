package com.daeseong.encryptedsharedpreference_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.daeseong.encryptedsharedpreference_test.util.EncryptedSharedPreferencesUtil

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private val REQUEST_DATA = 1
    private val RESULT_OK = 0

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener {
            Save()
        }

        button2.setOnClickListener {
            Remove()
        }

        button3.setOnClickListener {
            getData()
        }

        button4.setOnClickListener {
            Clear()
        }

        try {

            //조회
            val sLoadID = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sID", "") as String
            val sLoadPassword = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sPassword", "") as String
            val sLoadMemberNumber = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sMemberNumber", 1) as Int
            val sLoadSaved = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sSaved", false) as Boolean
            val sLoadtemp = EncryptedSharedPreferencesUtil.getInstance(this).getValue("stemp", "") as String

            Log.e(tag, "sLoadID:$sLoadID")
            Log.e(tag, "sLoadPassword:$sLoadPassword")
            Log.e(tag, "sLoadMemberNumber:$sLoadMemberNumber")
            Log.e(tag, "sLoadSaved:$sLoadSaved")
            Log.e(tag, "sLoadtemp:$sLoadtemp")

            editText1.setText(sLoadID)
            editText2.setText(sLoadPassword)
            editText3.setText(sLoadMemberNumber.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_DATA && resultCode == RESULT_OK) {

            val sID = data?.getStringExtra("sID") ?: ""
            val sPassword = data?.getStringExtra("sPassword") ?: ""
            val sMemberNumber = data?.getStringExtra("sMemberNumber") ?: ""

            editText1.setText(sID)
            editText2.setText(sPassword)
            editText3.setText(sMemberNumber)

            EncryptedSharedPreferencesUtil.getInstance(this).apply {
                setValue("sID", sID)
                setValue("sPassword", sPassword)
                setValue("sMemberNumber", sMemberNumber.toInt())
            }
        }
    }

    private fun Save() {

        val sID = editText1!!.text.toString()
        val sPassword = editText2!!.text.toString()
        val sMemberNumber = editText3!!.text.toString()

        if (TextUtils.isEmpty(sID)) {
            //Log.e(tag, "sID=null")
            return
        }
        if (TextUtils.isEmpty(sPassword)) {
            //Log.e(tag, "sPassword=null")
            return
        }
        if (TextUtils.isEmpty(sMemberNumber)) {
            //Log.e(tag, "sMemberNumber=null")
            return
        }

        //저장
        EncryptedSharedPreferencesUtil.getInstance(this).setValue("sID", sID)
        EncryptedSharedPreferencesUtil.getInstance(this).setValue("sPassword", sPassword)
        EncryptedSharedPreferencesUtil.getInstance(this).setValue("sMemberNumber", sMemberNumber.toInt())
        EncryptedSharedPreferencesUtil.getInstance(this).setValue("sSaved", true)
        EncryptedSharedPreferencesUtil.getInstance(this).setValue("stemp", "")
        editText1!!.setText("")
        editText2!!.setText("")
        editText3!!.setText("")
        editText1!!.requestFocus()
    }

    private fun Clear() {
        // 전체 해제
        EncryptedSharedPreferencesUtil.getInstance(this).clear()
        clearEditTexts()
    }

    private fun Remove() {
        // 전체 삭제
        val allKeys = EncryptedSharedPreferencesUtil.getInstance(this).getAll()?.keys
        allKeys?.forEach { key ->
            Log.e(tag, "sKey:$key")
            EncryptedSharedPreferencesUtil.getInstance(this).remove(key)
        }
        clearEditTexts()
    }

    private fun getData() {

        val sLoadID = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sID", "") as String
        val sLoadPassword = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sPassword", "") as String
        val sLoadMemberNumber = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sMemberNumber", 1) as Int
        val sLoadSaved = EncryptedSharedPreferencesUtil.getInstance(this).getValue("sSaved", false) as Boolean
        val sLoadtemp = EncryptedSharedPreferencesUtil.getInstance(this).getValue("stemp", "") as String

        if (sLoadID.isEmpty() || sLoadPassword.isEmpty() || sLoadMemberNumber.toString().isEmpty()) {
           return
        }

        val intent = Intent(this, SharedResultActivity::class.java).apply {
            putExtra("sID", sLoadID)
            putExtra("sPassword", sLoadPassword)
            putExtra("sMemberNumber", sLoadMemberNumber)
            putExtra("sSaved", sLoadSaved)
            putExtra("stemp", sLoadtemp)
        }
        startActivityForResult(intent, REQUEST_DATA)
    }

    private fun clearEditTexts() {
        editText1.text.clear()
        editText2.text.clear()
        editText3.text.clear()
        editText1.requestFocus()
    }
}