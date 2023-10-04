package com.daeseong.sharedpreferences_test

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private val REQUEST_DATA = 1
    private val RESULT_OK = 0

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText

    val sharedPreferencesUtil = SharedPreferencesUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)

        try {

            val sLoadID = sharedPreferencesUtil.getValue(this, "sID", "") as String
            val sLoadPassword = sharedPreferencesUtil.getValue(this, "sPassword", "") as String
            val sLoadMemberNumber = sharedPreferencesUtil.getValue(this, "sMemberNumber", 1) as Int

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

            sharedPreferencesUtil.apply {
                setValue(this@MainActivity, "sID", sID)
                setValue(this@MainActivity, "sPassword", sPassword)
                setValue(this@MainActivity, "sMemberNumber", sMemberNumber.toInt())
            }
        }
    }

    fun btn_Save(v: View?) {
        val sID = editText1.text.toString()
        val sPassword = editText2.text.toString()
        val sMemberNumber = editText3.text.toString()

        if (TextUtils.isEmpty(sID) || TextUtils.isEmpty(sPassword) || TextUtils.isEmpty(sMemberNumber)) {
            return
        }

        sharedPreferencesUtil.apply {
            setValue(this@MainActivity, "sID", sID)
            setValue(this@MainActivity, "sPassword", sPassword)
            setValue(this@MainActivity, "sMemberNumber", sMemberNumber.toInt())
            setValue(this@MainActivity, "sSaved", true)
            setValue(this@MainActivity, "stemp", "")
        }

        editText1.setText("")
        editText2.setText("")
        editText3.setText("")
        editText1.requestFocus()
    }

    fun btn_Clear(v: View?) {
        sharedPreferencesUtil.clear(this)
        editText1.setText("")
        editText2.setText("")
        editText3.setText("")
        editText1.requestFocus()
    }

    fun btn_Remove(v: View?) {

        val alls: Map<String, *> = sharedPreferencesUtil.getAll(this)

        alls.keys.forEach {
            sharedPreferencesUtil.remove(this, it)
        }

        editText1.setText("")
        editText2.setText("")
        editText3.setText("")
        editText1.requestFocus()
    }

    fun btn_Get(v: View?) {

        val sLoadID = sharedPreferencesUtil.getValue(this, "sID", "") as String
        val sLoadPassword = sharedPreferencesUtil.getValue(this, "sPassword", "") as String
        val sLoadMemberNumber = sharedPreferencesUtil.getValue(this, "sMemberNumber", 1) as Int

        if (TextUtils.isEmpty(sLoadID) || TextUtils.isEmpty(sLoadPassword) || TextUtils.isEmpty(sLoadMemberNumber.toString())) {
            Log.d(tag, "sID, sPassword, or sMemberNumber is null")
            return
        }

        val intent = Intent(this, SharedResultActivity::class.java).apply {
            putExtra("sID", sLoadID)
            putExtra("sPassword", sLoadPassword)
            putExtra("sMemberNumber", sLoadMemberNumber)
            putExtra("sSaved", sharedPreferencesUtil.getValue(this@MainActivity, "sSaved", false) as Boolean)
            putExtra("stemp", sharedPreferencesUtil.getValue(this@MainActivity, "stemp", "") as String)
        }

        startActivityForResult(intent, REQUEST_DATA)
    }
}
