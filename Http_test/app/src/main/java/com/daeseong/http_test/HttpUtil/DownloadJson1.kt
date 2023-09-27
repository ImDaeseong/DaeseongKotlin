import android.app.ProgressDialog
import android.os.AsyncTask
import com.daeseong.http_test.ImageTextView2Activity
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadJson1(private val imageTextView2Activity: ImageTextView2Activity) : AsyncTask<Void, Void, String>() {

    private val url1 = "https://api.bithumb.com/public/ticker/BTC"

    private val progressDialog: ProgressDialog = ProgressDialog.show(imageTextView2Activity, "접속중...", "데이터 다운로드중...", true)

    override fun doInBackground(vararg params: Void?): String? {

        var httpURLConnection: HttpURLConnection? = null
        val stringBuilder = StringBuilder()

        try {

            val url = URL(url1)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connect()

            val resCode: Int = httpURLConnection.responseCode
            if (resCode == HttpURLConnection.HTTP_OK) {
                val inputStream = BufferedInputStream(httpURLConnection.inputStream)
                val reader = BufferedReader(InputStreamReader(inputStream))

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
                inputStream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            httpURLConnection?.disconnect()
        }

        return stringBuilder.toString()
    }

    override fun onPostExecute(s: String) {
        super.onPostExecute(s)

        progressDialog.dismiss()
        imageTextView2Activity.loadJsonData(s)
    }
}
