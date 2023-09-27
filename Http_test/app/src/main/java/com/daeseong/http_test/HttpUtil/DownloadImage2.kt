import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.daeseong.http_test.ImageTextView2Activity
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadImage2(private val imageTextView2Activity: ImageTextView2Activity) : AsyncTask<Void, Void, Bitmap?>() {

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(imageTextView2Activity).apply {
            setMessage("이미지 다운로드중...")
            setCancelable(true)
        }
    }

    private val url1 = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.show()
    }

    override fun doInBackground(vararg params: Void?): Bitmap? {
        return try {
            val url = URL(url1)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.doInput = true
            httpURLConnection.connect()
            val inputStream: InputStream = httpURLConnection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            httpURLConnection.disconnect()
            inputStream.close()
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        super.onPostExecute(bitmap)

        progressDialog.dismiss()

        if (bitmap != null) {
            imageTextView2Activity.setImageViewBitmap(bitmap)
        }
    }
}
