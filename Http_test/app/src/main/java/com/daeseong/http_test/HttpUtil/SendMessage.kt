import android.content.Context
import android.net.ConnectivityManager
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class SendMessage(private val context: Context, private val textView: TextView) {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend fun sendPostRequest(urlText: String, urlParameters: String) {
        withContext(Dispatchers.IO) {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                try {
                    val postData = urlParameters.toByteArray(StandardCharsets.UTF_8)
                    val postDataLength = postData.size
                    val url = URL(urlText)

                    val httpURLConnection = url.openConnection() as HttpURLConnection
                    httpURLConnection.requestMethod = "POST"
                    httpURLConnection.doInput = true
                    httpURLConnection.doOutput = true
                    httpURLConnection.instanceFollowRedirects = false
                    httpURLConnection.setRequestProperty(
                        "Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8"
                    )
                    httpURLConnection.setRequestProperty("Content-Length", postDataLength.toString())

                    httpURLConnection.useCaches = false
                    httpURLConnection.connect()

                    DataOutputStream(httpURLConnection.outputStream).use { dataOutputStream ->
                        dataOutputStream.write(postData)
                        dataOutputStream.flush()
                    }

                    val responseCode: Int = httpURLConnection.responseCode
                    val inputStream: InputStream = if (responseCode == HttpURLConnection.HTTP_OK) {
                        httpURLConnection.inputStream
                    } else {
                        httpURLConnection.errorStream
                    }

                    val response = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8)).use { reader ->
                        val stringBuilder = StringBuilder()
                        reader.forEachLine { line ->
                            stringBuilder.append(line).append("\n")
                        }
                        stringBuilder.toString()
                    }
                    withContext(Dispatchers.Main) {
                        textView.text = response
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
