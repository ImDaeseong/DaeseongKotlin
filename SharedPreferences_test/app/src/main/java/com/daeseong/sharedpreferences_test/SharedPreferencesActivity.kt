package com.daeseong.sharedpreferences_test

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

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
            val sLoadID = SharedPreferences_util().getValue(this, "sID", "") as String
            val sLoadPassword = SharedPreferences_util().getValue(this, "sPassword", "") as String
            val sLoadMemberNumber = SharedPreferences_util().getValue(this, "sMemberNumber", -1) as Int
            val sLoadSaved = SharedPreferences_util().getValue(this, "sSaved", false) as Boolean
            val sLoadtemp = SharedPreferences_util().getValue(this, "stemp", "") as String

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
