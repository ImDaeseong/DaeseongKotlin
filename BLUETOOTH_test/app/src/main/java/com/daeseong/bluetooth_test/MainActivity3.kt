package com.daeseong.bluetooth_test

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity3 : AppCompatActivity() {

    private val TAG = MainActivity3::class.java.simpleName

    private var tv1: TextView? = null
    private var button1 : Button? = null

    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tv1 = findViewById<TextView>(R.id.tv1)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            if(checkPermission()){

                requestAdapter()

                var pairedDevices = bluetoothAdapter!!.bondedDevices

                if (pairedDevices.size > 0) {

                    //Log.e(TAG, "페어링된 장치 존재")

                    val stringBuilder = StringBuilder()
                    for (device in pairedDevices) {

                        //Log.e(TAG, device.name + " : " + device.address)
                        stringBuilder.append("${device.name} : ${device.address}\n")
                    }
                    tv1!!.text = stringBuilder.toString()

                } else {

                    tv1!!.text = "페어링된 장치 미존재"
                }

            }
        }
    }

    private fun requestAdapter() {

        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if(bluetoothAdapter != null){

                if (!bluetoothAdapter!!.isEnabled) {
                    val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    result.launch(intent)
                }
            }
        }catch (ex: java.lang.Exception) {
            Log.e(TAG, "Exception:${ex.message.toString()}")
        }
    }

    private fun checkPermission(): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            //sdk 31
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_CONNECT),1)
                return false
            }
        }
        return true
    }

    private val result =  registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        //Log.e(TAG, "result:$result.resultCode")

        if (result.resultCode == RESULT_OK) {
            Log.e(TAG, "$result.resultCode : 블루투스 On")
        } else if (result.resultCode == RESULT_CANCELED) {
            Log.e(TAG, "$result.resultCode : 블루투스 Off")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //Log.e(TAG, "onRequestPermissionsResult: $requestCode")

        //미승인시 재요청
        if (requestCode == 1) {

            if (grantResults.isNotEmpty()) {

                val result = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (!result) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_CONNECT)) {
                            requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_CONNECT), 1)
                        }
                    }
                }
            }
        }
    }
}