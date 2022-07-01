package com.daeseong.encryptedsharedpreference_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.daeseong.encryptedsharedpreference_test.util.encryptedsharedpreference_util

class SharedPreferencesActivity : AppCompatActivity() {

    private val tag: String = SharedPreferencesActivity::class.java.simpleName

    private var editText5: EditText? = null
    private var editText6: EditText? = null
    private var editText7: EditText? = null
    private var editText8: EditText? = null
    private var editText9: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        editText5 = findViewById<EditText>(R.id.editText5)
        editText6 = findViewById<EditText>(R.id.editText6)
        editText7 = findViewById<EditText>(R.id.editText7)
        editText8 = findViewById<EditText>(R.id.editText8)
        editText9 = findViewById<EditText>(R.id.editText9)

        try {

            //조회
            val sLoadID = encryptedsharedpreference_util.getInstance(this).getValue("sID", "") as String
            val sLoadPassword = encryptedsharedpreference_util.getInstance(this).getValue("sPassword", "") as String
            val sLoadMemberNumber = encryptedsharedpreference_util.getInstance(this).getValue("sMemberNumber", -1) as Int
            val sLoadSaved = encryptedsharedpreference_util.getInstance(this).getValue("sSaved", false) as Boolean
            val sLoadtemp = encryptedsharedpreference_util.getInstance(this).getValue("stemp", "") as String

            //Log.d(tag, "sLoadID:$sLoadID")
            //Log.d(tag, "sLoadPassword:$sLoadPassword")
            //Log.d(tag, "sLoadMemberNumber:$sLoadMemberNumber")
            //Log.d(tag, "sLoadSaved:$sLoadSaved")
            //Log.d(tag, "sLoadtemp:$sLoadtemp")

            if (sLoadSaved) editText8!!.setText("true")
            else editText8!!.setText("false")

            editText5!!.setText(sLoadID)
            editText6!!.setText(sLoadPassword)
            editText7!!.setText(sLoadMemberNumber.toString())
            editText9!!.setText(sLoadtemp)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}