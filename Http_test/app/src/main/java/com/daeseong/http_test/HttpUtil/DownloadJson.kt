import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class DownloadJson(private val textView: TextView) {

    fun execute(urlText: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = downloadJson(urlText)

            withContext(Dispatchers.Main) {
                try {
                    result?.let {
                        textView.text = it
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun downloadJson(urlText: String?): String? {
        return try {
            val url = URL(urlText)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val resCode: Int = connection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream.bufferedReader().use { reader ->
                    reader.readText()
                }
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
