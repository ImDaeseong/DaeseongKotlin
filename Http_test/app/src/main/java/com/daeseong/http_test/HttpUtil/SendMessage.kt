import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.widget.TextView
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class SendMessage(context: Context, textView: TextView) : AsyncTask<String?, Void?, String?>() {

    private val tag: String = SendMessage::class.java.simpleName
    private val context: Context = context
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val textView: TextView = textView

    override fun doInBackground(vararg params: String?): String? {
        val urlText = params[0]
        val urlParameters = params[1]

        try {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                val postData = urlParameters?.toByteArray(StandardCharsets.UTF_8)
                val postDataLength = postData?.size ?: 0
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

                val dataOutputStream = DataOutputStream(httpURLConnection.outputStream)
                dataOutputStream.write(postData)
                dataOutputStream.flush()
                dataOutputStream.close()

                val resCode: Int = httpURLConnection.responseCode
                val inputStream: InputStream = if (resCode == HttpURLConnection.HTTP_OK) {
                    httpURLConnection.inputStream
                } else {
                    httpURLConnection.errorStream
                }

                val reader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
                val stringBuilder = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append("\n")
                }
                inputStream.close()

                return stringBuilder.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(s: String?) {
        super.onPostExecute(s)

        try {
            s?.let {
                textView.text = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
