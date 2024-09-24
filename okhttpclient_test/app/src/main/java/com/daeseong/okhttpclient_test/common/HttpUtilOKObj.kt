import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

object HttpUtilOKObj {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    fun getDataResult(sUrl: String, callback: Callback) {
        val request = Request.Builder()
            .url(sUrl)
            .build()
        client.newCall(request).enqueue(callback)
    }

    fun sendDataResult(sUrl: String, sParams: String, callback: Callback) {
        val requestBody = sParams.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url(sUrl)
            .post(requestBody)
            .build()
        client.newCall(request).enqueue(callback)
    }

    fun cancelAll() {
        client.dispatcher.cancelAll()
    }
}