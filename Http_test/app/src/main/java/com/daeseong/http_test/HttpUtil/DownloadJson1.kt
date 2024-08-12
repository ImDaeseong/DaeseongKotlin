import android.app.ProgressDialog
import com.daeseong.http_test.ImageTextView2Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadJson1(private val imageTextView2Activity: ImageTextView2Activity) {

    private val url1 = "https://api.bithumb.com/public/ticker/BTC"

    suspend fun downloadJson() {

        val progressDialog = ProgressDialog.show(imageTextView2Activity, "접속중...", "데이터 다운로드중...", true)

        val result = withContext(Dispatchers.IO) {

            val stringBuilder = StringBuilder()
            try {
                val url = URL(url1)
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = BufferedInputStream(connection.inputStream)
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    reader.forEachLine { stringBuilder.append(it) }
                    reader.close()
                }
                connection.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            stringBuilder.toString()
        }

        progressDialog.dismiss()
        imageTextView2Activity.loadJsonData(result)
    }
}
