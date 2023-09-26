import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.net.URL

class ImageDownloader(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

    companion object {
        private val TAG = ImageDownloader::class.java.name
    }

    override fun doInBackground(vararg params: String): Bitmap? {
        return try {
            BitmapFactory.decodeStream(URL(params[0]).openStream())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        try {
            bitmap?.let {
                imageView.setImageBitmap(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCancelled() {
        Log.e(TAG, "onCancelled")
    }
}