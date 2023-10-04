package com.daeseong.encryptedsharedpreference_test

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SharedResultActivity : AppCompatActivity() {

    private val tag: String = SharedResultActivity::class.java.simpleName

    private val REQUEST_DATA = 1
    private val RESULT_OK = 0

    private lateinit var editText10: EditText
    private lateinit var editText11: EditText
    private lateinit var editText12: EditText
    private lateinit var editText13: EditText
    private lateinit var editText14: EditText
    private lateinit var btn_change: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_result)

        editText10 = findViewById(R.id.editText10)
        editText11 = findViewById(R.id.editText11)
        editText12 = findViewById(R.id.editText12)
        editText13 = findViewById(R.id.editText13)
        editText14 = findViewById(R.id.editText14)

        btn_change = findViewById(R.id.btn_change)
        btn_change.setOnClickListener {
            changeData()
        }

        try {
            intent.apply {
                val sLoadID = getStringExtra("sID")
                val sLoadPassword = getStringExtra("sPassword")
                val sLoadMemberNumber = getIntExtra("sMemberNumber", 1)
                val sLoadSaved = getBooleanExtra("sSaved", false)
                val sLoadtemp = getStringExtra("stemp")

                if (sLoadSaved) editText13.setText("true")
                else editText13.setText("false")

                editText10.setText(sLoadID)
                editText11.setText(sLoadPassword)
                editText12.setText(sLoadMemberNumber.toString())
                editText14.setText(sLoadtemp)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        // 백버튼 기능 막음
        return
    }

    private fun changeData() {

        val sID = editText10.text.toString()
        val sPassword = editText11.text.toString()
        val sLoadMemberNumber = editText12.text.toString()

        if (TextUtils.isEmpty(sID)) {
            Log.d(tag, "sID=null")
            return
        }
        if (TextUtils.isEmpty(sPassword)) {
            Log.d(tag, "sPassword=null")
            return
        }

        val intent = Intent().apply {
            putExtra("sID", sID)
            putExtra("sPassword", sPassword)
            putExtra("sMemberNumber", sLoadMemberNumber)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}
