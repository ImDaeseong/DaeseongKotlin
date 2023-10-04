package com.daeseong.encryptedsharedpreference_test

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.encryptedsharedpreference_test.util.EncryptedSharedPreferencesUtil

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
            val sharedPreferencesUtil = EncryptedSharedPreferencesUtil.getInstance(this)
            val sLoadID = sharedPreferencesUtil.getValue("sID", "") as String
            val sLoadPassword = sharedPreferencesUtil.getValue("sPassword", "") as String
            val sLoadMemberNumber = sharedPreferencesUtil.getValue("sMemberNumber", -1) as Int
            val sLoadSaved = sharedPreferencesUtil.getValue("sSaved", false) as Boolean
            val sLoadtemp = sharedPreferencesUtil.getValue("stemp", "") as String

            Log.e(tag, "sLoadID:$sLoadID")
            Log.e(tag, "sLoadPassword:$sLoadPassword")
            Log.e(tag, "sLoadMemberNumber:$sLoadMemberNumber")
            Log.e(tag, "sLoadSaved:$sLoadSaved")
            Log.e(tag, "sLoadtemp:$sLoadtemp")

            editText8.setText(if (sLoadSaved) "true" else "false")
            editText5.setText(sLoadID)
            editText6.setText(sLoadPassword)
            editText7.setText(sLoadMemberNumber.toString())
            editText9.setText(sLoadtemp)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
