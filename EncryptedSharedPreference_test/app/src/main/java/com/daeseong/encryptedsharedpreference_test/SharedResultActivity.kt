package com.daeseong.encryptedsharedpreference_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText

class SharedResultActivity : AppCompatActivity() {

    private val tag: String = SharedResultActivity::class.java.simpleName

    private val REQUEST_DATA = 1
    private val RESULT_OK = 0

    private var editText10: EditText? = null
    private var editText11: EditText? = null
    private var editText12: EditText? = null
    private var editText13: EditText? = null
    private var editText14: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_result)

        editText10 = findViewById<EditText>(R.id.editText10)
        editText11 = findViewById<EditText>(R.id.editText11)
        editText12 = findViewById<EditText>(R.id.editText12)
        editText13 = findViewById<EditText>(R.id.editText13)
        editText14 = findViewById<EditText>(R.id.editText14)

        try {

            val sLoadID = intent.getStringExtra("sID")
            val sLoadPassword = intent.getStringExtra("sPassword")
            val sLoadMemberNumber = intent.getIntExtra("sMemberNumber", 1)
            val sLoadSaved = intent.getBooleanExtra("sSaved", false)
            val sLoadtemp = intent.getStringExtra("stemp")

            //Log.d(tag, "sLoadID:$sLoadID")
            //Log.d(tag, "sLoadPassword:$sLoadPassword")
            //Log.d(tag, "sLoadMemberNumber:$sLoadMemberNumber")
            //Log.d(tag, "sLoadSaved:$sLoadSaved")
            //Log.d(tag, "sLoadtemp:$sLoadtemp")

            if (sLoadSaved) editText13!!.setText("true")
            else editText13!!.setText("false")

            editText10!!.setText(sLoadID)
            editText11!!.setText(sLoadPassword)
            editText12!!.setText(sLoadMemberNumber.toString())
            editText14!!.setText(sLoadtemp)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun btn_change(v: View?) {
        ChangeData()
    }

    override fun onBackPressed() {
        //super.onBackPressed();
        //백버튼 기능 막음
        return
    }

    private fun ChangeData() {

        val sID = editText10!!.text.toString()
        val sPassword = editText11!!.text.toString()
        val sLoadMemberNumber = editText12!!.text.toString()

        if (TextUtils.isEmpty(sID)) {
            Log.d(tag, "sID=null")
            return
        }
        if (TextUtils.isEmpty(sPassword)) {
            Log.d(tag, "sPassword=null")
            return
        }

        val intent = Intent()
        intent.putExtra("sID", sID)
        intent.putExtra("sPassword", sPassword)
        intent.putExtra("sMemberNumber", sLoadMemberNumber)
        setResult(RESULT_OK, intent)
        finish()
    }
}