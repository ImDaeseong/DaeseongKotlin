package com.daeseong.banner_test

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class Banner2Activity : AppCompatActivity() {

    private val tag = Banner2Activity::class.java.simpleName

    private var lastTimeBackPressed: Long = 0

    private var listView1: ListView? = null
    private var button1: Button? = null
    private var listViewAdapter: ListViewAdapter? = null
    private var banner1Task: Banner1Task? = null


    val url1 = "https://.png"
    val url2 = "https://.png"
    val url3 = "https://.png"
    val url4 = "https://.png"
    val url5 = "https://.png"
    val url6 = "https://.png"
    val url7 = "https://.png"
    val url8 = "https://.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner2)

        listView1 = findViewById<ListView>(R.id.listView1)
        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            banner1Task = Banner1Task(this)
            banner1Task!!.execute(url1, url2, url3, url4, url5, url6, url7, url8)
        }
    }

    override fun onBackPressed() {

        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finish()
            return
        }

        Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        lastTimeBackPressed = System.currentTimeMillis()
    }

    inner class Banner1Task(private val activity: Activity) : AsyncTask<String, Int, List<RowItem>>() {

        private var progressDialog: ProgressDialog? = null
        private var rowItems: MutableList<RowItem>? = null
        private var nTotalCount = 0

        override fun onPreExecute() {

            progressDialog = ProgressDialog(activity)
            progressDialog!!.setTitle("setTitle")
            progressDialog!!.setMessage("setMessage")
            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressDialog!!.isIndeterminate = false
            progressDialog!!.max = 100
            progressDialog!!.setIcon(R.drawable.number1)
            progressDialog!!.setCancelable(true)
            progressDialog!!.setOnCancelListener {

                Log.e(tag, "AsyncTask onCancelled")
                cancel(true)
                finish()
            }
            progressDialog!!.show()
        }

        override fun doInBackground(vararg params: String): List<RowItem> {

            nTotalCount = params.size

            var bitmap: Bitmap? = null
            rowItems = ArrayList()

            for (url in params) {

                bitmap = DownLoadImage(url)

                if (bitmap == null) continue

                rowItems!!.add(RowItem(bitmap))

                if (isCancelled) {
                    Log.e(tag, "doInBackground isCancelled")
                    break
                }
            }

            return rowItems!!
        }

        override fun onPostExecute(rowItems: List<RowItem>) {

            listViewAdapter = ListViewAdapter(activity, rowItems)
            listView1!!.adapter = listViewAdapter
            progressDialog!!.dismiss()
        }

        override fun onProgressUpdate(vararg values: Int?) {

            progressDialog!!.progress = values[0]!!

            if (rowItems != null) {

                Log.e(tag, "onProgressUpdate:" + (rowItems!!.size + 1) + "/" + nTotalCount)

                progressDialog!!.setMessage("Loading " + (rowItems!!.size + 1) + "/" + nTotalCount)
            }
        }

        override fun onCancelled() {
            super.onCancelled()

            Log.e(tag, "AsyncTask onCancelled")
        }

        private fun DownLoadImage(sUrl: String): Bitmap? {

            var httpURLConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            val bufferedReader: BufferedReader? = null
            var bitmap: Bitmap? = null

            try {
                val url = URL(sUrl)

                httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.allowUserInteraction = false
                httpURLConnection.instanceFollowRedirects = true
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.connect()

                val resCode: Int = httpURLConnection.responseCode
                if (resCode != HttpURLConnection.HTTP_OK) {
                    return null
                }

                inputStream = httpURLConnection.getInputStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
                httpURLConnection.disconnect()

            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return bitmap
        }

    }

    inner class ListViewAdapter(context: Context, rowItems: List<RowItem>) :  BaseAdapter() {

        var context: Context = context
        var rowItems: List<RowItem> = rowItems

        override fun getCount(): Int {
            return rowItems.size
        }

        override fun getItem(position: Int): Any {
            return rowItems[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var convertView: View? = convertView
            var holder: ViewHolder? = null

            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.list_item1, null)
                holder = ViewHolder()
                holder.imageView = convertView.findViewById(R.id.thumbnail) as ImageView
                convertView.tag = holder

            } else {
                holder = convertView.tag as ViewHolder?
            }

            val (bitmap) = getItem(position) as RowItem
            holder!!.imageView!!.setImageBitmap(bitmap)

            return convertView!!
        }

    }

    inner class ViewHolder {
        var imageView: ImageView? = null
    }
}
