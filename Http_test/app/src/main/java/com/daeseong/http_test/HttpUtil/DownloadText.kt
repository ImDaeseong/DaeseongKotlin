import android.os.AsyncTask
import android.widget.TextView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class DownloadText(private val textView: TextView) : AsyncTask<String?, Void?, String?>() {

    override fun doInBackground(vararg params: String?): String? {

        val urlText = params[0]

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
