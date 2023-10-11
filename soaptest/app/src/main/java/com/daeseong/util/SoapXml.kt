package com.daeseong.util

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class SoapXml(private val textView: TextView) : AsyncTask<String?, Void?, String?>() {

    companion object {
        private const val TAG = "SoapXml"
        private const val NAME_SPACE = "http://www.daeseong.com/"
        private const val METHOD_NAME = "findUserList"
        private const val SOAP_URL = "http://www.daeseong.com/MyWebService.asmx"
        private const val SOAP_ACTION = "http://www.daeseong.com/findUserList"
    }

    private var sResult: String? = null

    override fun doInBackground(vararg params: String?): String? {

        val soapObject = SoapObject(NAME_SPACE, METHOD_NAME)
        soapObject.addProperty("keyword", params[0]) //파라미터 추가

        val soapSerializationEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        soapSerializationEnvelope.dotNet = true //닷넷 사용 유무
        soapSerializationEnvelope.setOutputSoapObject(soapObject)

        val httpTransportSE = HttpTransportSE(SOAP_URL)

        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope)

            val soapbody = soapSerializationEnvelope.bodyIn as SoapObject
            sResult = soapbody.toString()

        } catch (ex: Exception) {
            Log.e(TAG, "Exception: ${ex.message}")
        }

        return sResult
    }

    override fun onPostExecute(sResult: String?) {
        super.onPostExecute(sResult)

        try {
            sResult?.let {
                textView.text = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}