package com.daeseong.util

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class SoapXml(private val textView: TextView) {

    companion object {
        private const val TAG = "SoapXml"
        private const val NAME_SPACE = "http://www.daeseong.com/"
        private const val METHOD_NAME = "findUserList"
        private const val SOAP_URL = "http://www.daeseong.com/MyWebService.asmx"
        private const val SOAP_ACTION = "http://www.daeseong.com/findUserList"
    }

    fun execute(keyword: String?) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) {
                callSoapWebService(keyword)
            }
            result?.let {
                textView.text = it
            }
        }
    }

    private suspend fun callSoapWebService(keyword: String?): String? {
        val soapObject = SoapObject(NAME_SPACE, METHOD_NAME).apply {
            addProperty("keyword", keyword) // 파라미터 추가
        }

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11).apply {
            dotNet = true // 닷넷 사용 유무
            setOutputSoapObject(soapObject)
        }

        return try {
            val httpTransportSE = HttpTransportSE(SOAP_URL)
            httpTransportSE.call(SOAP_ACTION, envelope)

            (envelope.bodyIn as? SoapObject)?.toString()
        } catch (ex: Exception) {
            Log.e(TAG, "Exception: ${ex.message}")
            null
        }
    }
}
