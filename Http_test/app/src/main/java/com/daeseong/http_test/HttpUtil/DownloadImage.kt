import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadImage(private val imageView: ImageView) : AsyncTask<String?, Void, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? {

        val urlImage = params[0]
        var bitmap: Bitmap? = null

        try {
            val url = URL(urlImage)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()

            val resCode: Int = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                val inputStream: InputStream = httpURLConnection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
            }

            httpURLConnection.disconnect()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bitmap
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        super.onPostExecute(bitmap)

        bitmap?.let {
            imageView.setImageBitmap(it)
        }
    }
}
