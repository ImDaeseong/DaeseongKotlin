package com.daeseong.util

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class SoapXml(textView: TextView) : AsyncTask<String?, Void?, String?>() {

    private val TAG = SoapXml::class.java.simpleName

    private val NAME_SPACE = "http://www.daeseong.com/"
    private val METHOD_NAME = "findUserList"
    private val SOAP_URL = "http://www.daeseong.com/MyWebService.asmx"
    private val SOAP_ACTION = "http://www.daeseong.com/findUserList"

    private var sResult: String? = null

    private val textView: TextView = textView

    override fun doInBackground(vararg params: String?): String? {

        val soapObject = SoapObject(NAME_SPACE, METHOD_NAME)
        soapObject.addProperty("keyword", params[0]) //파라미터 추가

        val soapSerializationEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        soapSerializationEnvelope.dotNet = true //닷넷 사용 유무
        soapSerializationEnvelope.setOutputSoapObject(soapObject)

        val httpTransportSE = HttpTransportSE(SOAP_URL)

        try {

            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope)

            //val oResult = soapSerializationEnvelope.response
            //Log.e(TAG, "getResponse:$oResult")

            val soapbody = soapSerializationEnvelope.bodyIn as SoapObject
            sResult = soapbody.toString()
            //Log.e(TAG, "bodyIn:$sResult")

        } catch (ex: Exception) {
            Log.e(TAG, "Exception:" + ex.message.toString())
        }

        return sResult
    }

    override fun onPostExecute(sResult: String?) {
        super.onPostExecute(sResult)

        try {

            if (sResult != null) {
                textView.text = sResult.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
