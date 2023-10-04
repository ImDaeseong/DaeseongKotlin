package com.daeseong.sharedpreferences_test

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_result)

        editText10 = findViewById(R.id.editText10)
        editText11 = findViewById(R.id.editText11)
        editText12 = findViewById(R.id.editText12)
        editText13 = findViewById(R.id.editText13)
        editText14 = findViewById(R.id.editText14)

        try {
            val sLoadID = intent.getStringExtra("sID")
            val sLoadPassword = intent.getStringExtra("sPassword")
            val sLoadMemberNumber = intent.getIntExtra("sMemberNumber", 1)
            val sLoadSaved = intent.getBooleanExtra("sSaved", false)
            val sLoadtemp = intent.getStringExtra("stemp")

            if (sLoadSaved) editText13.setText("true")
            else editText13.setText("false")

            editText10.setText(sLoadID)
            editText11.setText(sLoadPassword)
            editText12.setText(sLoadMemberNumber.toString())
            editText14.setText(sLoadtemp)

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

        val sID = editText10.text.toString()
        val sPassword = editText11.text.toString()
        val sLoadMemberNumber = editText12.text.toString()

        if (TextUtils.isEmpty(sID) || TextUtils.isEmpty(sPassword)) {
            // Log.d(tag, "sID or sPassword is null")
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
