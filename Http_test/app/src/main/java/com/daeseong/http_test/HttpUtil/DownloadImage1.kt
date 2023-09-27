import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.daeseong.http_test.ImageView2Activity
import java.io.IOException
import java.io.InputStream
import java.net.URL

class DownloadImage1(private val imageView2Activity: ImageView2Activity) : AsyncTask<String?, Void, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? {
        return try {
            val url = URL(params[0])
            val inputStream: InputStream = url.openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        if (bitmap != null) {
            imageView2Activity.setImageViewBitmap(bitmap)
        }
    }
}
