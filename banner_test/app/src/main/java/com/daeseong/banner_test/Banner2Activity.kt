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

    private val urlList = listOf(
        "https://.png",
        "https://.png",
        "https://.png",
        "https://.png",
        "https://.png",
        "https://.png",
        "https://.png",
        "https://.png"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner2)

        listView1 = findViewById(R.id.listView1)

        button1 = findViewById(R.id.button1)
        button1?.setOnClickListener {
            banner1Task = Banner1Task(this)
            banner1Task?.execute(*urlList.toTypedArray())
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
            progressDialog?.apply {
                setTitle("setTitle")
                setMessage("setMessage")
                setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                isIndeterminate = false
                max = 100
                setIcon(R.drawable.number1)
                setCancelable(true)
                setOnCancelListener {
                    Log.e(tag, "AsyncTask onCancelled")
                    cancel(true)
                    finish()
                }
                show()
            }
        }

        override fun doInBackground(vararg params: String): List<RowItem> {
            nTotalCount = params.size
            var bitmap: Bitmap? = null
            rowItems = ArrayList()

            for (url in params) {
                bitmap = downloadImage(url)
                if (bitmap == null) continue
                rowItems?.add(RowItem(bitmap))
                if (isCancelled) {
                    Log.e(tag, "doInBackground isCancelled")
                    break
                }
            }

            return rowItems ?: emptyList()
        }

        override fun onPostExecute(rowItems: List<RowItem>) {
            listViewAdapter = ListViewAdapter(activity, rowItems)
            listView1?.adapter = listViewAdapter
            progressDialog?.dismiss()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressDialog?.apply {
                progress = values[0]!!
                rowItems?.let {
                    Log.e(tag, "onProgressUpdate: ${it.size + 1}/$nTotalCount")
                    setMessage("Loading ${it.size + 1}/$nTotalCount")
                }
            }
        }

        override fun onCancelled() {
            super.onCancelled()
            Log.e(tag, "AsyncTask onCancelled")
        }

        private fun downloadImage(sUrl: String): Bitmap? {
            var httpURLConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
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

                inputStream = httpURLConnection.inputStream
                return BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
                httpURLConnection?.disconnect()
            }
            return null
        }
    }

    inner class ListViewAdapter(context: Context, private val rowItems: List<RowItem>) :
        BaseAdapter() {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return rowItems.size
        }

        override fun getItem(position: Int): Any {
            return rowItems[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var convertView = convertView
            var holder: ViewHolder? = null

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item1, parent, false)
                holder = ViewHolder()
                holder.imageView = convertView.findViewById(R.id.thumbnail)
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder?
            }

            val (bitmap) = getItem(position) as RowItem
            holder?.imageView?.setImageBitmap(bitmap)

            return convertView!!
        }
    }

    inner class ViewHolder {
        var imageView: ImageView? = null
    }
}
