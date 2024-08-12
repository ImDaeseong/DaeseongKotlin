import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.daeseong.http_test.ImageView2Activity
import java.io.IOException
import java.net.URL
import kotlin.concurrent.thread

class DownloadImage1(private val imageView2Activity: ImageView2Activity) {

    fun execute(url: String?) {
        thread {
            val bitmap = downloadImage(url)
            bitmap?.let {
                imageView2Activity.runOnUiThread {
                    imageView2Activity.setImageViewBitmap(it)
                }
            }
        }
    }

    private fun downloadImage(url: String?): Bitmap? {
        return try {
            val inputStream = URL(url).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
