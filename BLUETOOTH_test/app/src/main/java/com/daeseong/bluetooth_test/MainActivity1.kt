package com.daeseong.bluetooth_test

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity1 : AppCompatActivity() {

    private val TAG = MainActivity1::class.java.simpleName

    private var tv1: TextView? = null

    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        tv1 = findViewById<TextView>(R.id.tv1)

        if(checkPermission()) {

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if (bluetoothAdapter == null) {

                tv1!!.text = "장치가 블루투스를 지원되지 않습니다."

            } else {

                //Log.e(TAG, "bluetoothAdapter getState: " + bluetoothAdapter!!.state)

                when (bluetoothAdapter!!.state) {

                    BluetoothAdapter.STATE_ON -> {
                        tv1!!.text = "블루투스 활성화"
                    }

                    BluetoothAdapter.STATE_TURNING_ON -> {
                        tv1!!.text = "블루투스 활성화중"
                    }

                    BluetoothAdapter.STATE_TURNING_OFF -> {
                        tv1!!.text = "블루투스 비활성화중"
                    }

                    BluetoothAdapter.STATE_OFF -> {
                        tv1!!.text = "블루투스 비활성화"
                    }
                }
            }
        } else {
            requestAdapter()
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
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                    1
                )
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