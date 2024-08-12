import android.app.ProgressDialog
import android.graphics.BitmapFactory
import com.daeseong.http_test.ImageTextView2Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class DownloadImage2(private val imageTextView2Activity: ImageTextView2Activity) {

    private val url1 = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png"

    suspend fun downloadImage() {

        val progressDialog = ProgressDialog(imageTextView2Activity).apply {
            setMessage("이미지 다운로드중...")
            setCancelable(true)
            show()
        }

        val bitmap = withContext(Dispatchers.IO) {

            try {
                val url = URL(url1)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                connection.disconnect()
                bitmap
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        progressDialog.dismiss()

        if (bitmap != null) {
            imageTextView2Activity.setImageViewBitmap(bitmap)
        }
    }
}
