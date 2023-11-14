import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class BannerDisplayImage {

    suspend fun loadImage(sUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            downloadImage(sUrl)
        }
    }

    private fun downloadImage(sUrl: String): Bitmap? {
        var httpURLConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null

        try {
            val url = URL(sUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.allowUserInteraction = false
            httpURLConnection.instanceFollowRedirects = true
            httpURLConnection.requestMethod = "GET"

            val resCode: Int = httpURLConnection.responseCode
            if (resCode != HttpURLConnection.HTTP_OK) {
                return null
            }

            inputStream = httpURLConnection.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.closeQuietly()
            httpURLConnection?.disconnect()
        }

        return bitmap
    }

    private fun InputStream.closeQuietly() {
        try {
            this.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
