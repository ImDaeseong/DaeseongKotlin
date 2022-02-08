package com.daeseong.bluetooth_test

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity2 : AppCompatActivity() {

    private val TAG = MainActivity2::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null
    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //bluetooth on
        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            if(checkPermission()){
                requestAdapter(true)
            }
        }

        //bluetooth off
        button2 = findViewById<View>(R.id.button2) as Button
        button2!!.setOnClickListener {

            if(checkPermission()){
                requestAdapter(false)
            }
        }

        //블루투스 상태 변경 확인
        initFilter()
    }

    override fun onDestroy() {
        super.onDestroy()
        DestoryFilter()
    }

    private fun initFilter() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                if (intent.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                    val nState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    when (nState) {
                        BluetoothAdapter.STATE_OFF -> Log.e(TAG,"ACTION_STATE_CHANGED: 블루투스 비활성화")
                        BluetoothAdapter.STATE_TURNING_OFF -> Log.e(TAG,"ACTION_STATE_CHANGED: 블루투스 비활성화중")
                        BluetoothAdapter.STATE_ON -> Log.e(TAG,"ACTION_STATE_CHANGED: 블루투스 활성화")
                        BluetoothAdapter.STATE_TURNING_ON -> Log.e(TAG,"ACTION_STATE_CHANGED: 블루투스 활성화중")
                    }
                }
            }
        }

        //블루투스 상태 변환 감지
        intentFilter = IntentFilter()
        intentFilter!!.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun DestoryFilter() {

        try {

            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver)
            }

            if (bluetoothAdapter!!.isDiscovering) {
                bluetoothAdapter!!.cancelDiscovery()
            }

            if (bluetoothAdapter!!.state == BluetoothAdapter.STATE_ON) {
                bluetoothAdapter!!.disable()
            }

        }catch (ex: java.lang.Exception) {
            Log.e(TAG, "Exception:${ex.message.toString()}")
        }
    }

    private fun requestAdapter(bEnable : Boolean) {

        try {

            if (bEnable) {

                if (!bluetoothAdapter!!.isEnabled) {
                    val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    result.launch(intent)
                }

            } else {

                if (bluetoothAdapter!!.isEnabled)
                    bluetoothAdapter!!.disable()
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