import android.graphics.BitmapFactory
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadImage(private val imageView: ImageView) {

    private var job: Job? = null

    fun execute(urlImage: String) {
        job = CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                var httpURLConnection: HttpURLConnection? = null
                try {
                    val url = URL(urlImage)
                    httpURLConnection = url.openConnection() as HttpURLConnection
                    httpURLConnection.apply {
                        allowUserInteraction = false
                        instanceFollowRedirects = true
                        requestMethod = "GET"
                        connect()
                    }

                    val responseCode = httpURLConnection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        val inputStream: InputStream = httpURLConnection.inputStream
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        inputStream.close()
                        bitmap
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                } finally {
                    httpURLConnection?.disconnect()
                }
            }

            bitmap?.let {
                imageView.setImageBitmap(it)
            }
        }
    }

    fun cancel() {
        job?.cancel()
    }
}
