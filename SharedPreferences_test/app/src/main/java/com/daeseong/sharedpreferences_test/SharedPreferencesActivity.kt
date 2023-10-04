package com.daeseong.sharedpreferences_test

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SharedPreferencesActivity : AppCompatActivity() {

    private val tag: String = SharedPreferencesActivity::class.java.simpleName

    private lateinit var editText5: EditText
    private lateinit var editText6: EditText
    private lateinit var editText7: EditText
    private lateinit var editText8: EditText
    private lateinit var editText9: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        editText5 = findViewById(R.id.editText5)
        editText6 = findViewById(R.id.editText6)
        editText7 = findViewById(R.id.editText7)
        editText8 = findViewById(R.id.editText8)
        editText9 = findViewById(R.id.editText9)

        try {
            // 조회
            val sLoadID = SharedPreferencesUtil().getValue(this, "sID", "") as String
            val sLoadPassword = SharedPreferencesUtil().getValue(this, "sPassword", "") as String
            val sLoadMemberNumber = SharedPreferencesUtil().getValue(this, "sMemberNumber", -1) as Int
            val sLoadSaved = SharedPreferencesUtil().getValue(this, "sSaved", false) as Boolean
            val sLoadtemp = SharedPreferencesUtil().getValue(this, "stemp", "") as String

            if (sLoadSaved) editText8.setText("true")
            else editText8.setText("false")

            editText5.setText(sLoadID)
            editText6.setText(sLoadPassword)
            editText7.setText(sLoadMemberNumber.toString())
            editText9.setText(sLoadtemp)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
