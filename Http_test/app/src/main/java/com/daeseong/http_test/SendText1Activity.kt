package com.daeseong.http_test

import SendMessage
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SendText1Activity : AppCompatActivity() {

    private val tag: String = SendText1Activity::class.java.simpleName

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var button1: Button
    private lateinit var textView5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_text1)

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        button1 = findViewById(R.id.button1)
        textView5 = findViewById(R.id.textView5)

        button1.setOnClickListener {

            val sText1 = editText1.text.toString()
            val sText2 = editText2.text.toString()
            val sText3 = editText3.text.toString()
            val postParams = "USERNO=$sText1&NAME=$sText2&PHONE=$sText3"
            val url1 = "https://"
            val sendMessage = SendMessage(this@SendText1Activity, textView5)
            sendMessage.execute(url1, postParams)

            //첫번째 방법
            //val sendTask = SendTask()
            //sendTask.execute(postParams)
        }
    }

    inner class SendTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String? {
            val url1 = "https://"
            val sPostData = params[0]
            var httpURLConnection: HttpURLConnection? = null
            var dataOutputStream: DataOutputStream? = null
            var bufferedReader: BufferedReader? = null
            val stringBuilder = StringBuilder()

            try {
                val url = URL(url1)
                httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                httpURLConnection.doOutput = true
                httpURLConnection.connect()
                dataOutputStream = DataOutputStream(httpURLConnection.outputStream)
                dataOutputStream.writeBytes(sPostData)
                dataOutputStream.flush()
                dataOutputStream.close()

                val resCode: Int = httpURLConnection.responseCode
                bufferedReader = if (resCode == 200) {
                    BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                } else {
                    BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                }

                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                dataOutputStream?.close()
                bufferedReader?.close()
                httpURLConnection?.disconnect()
            }
            return stringBuilder.toString()
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            Log.e(tag, s)
        }
    }

    fun isConnection(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        return networkInfo.isConnected && networkInfo.isAvailable
    }
}
