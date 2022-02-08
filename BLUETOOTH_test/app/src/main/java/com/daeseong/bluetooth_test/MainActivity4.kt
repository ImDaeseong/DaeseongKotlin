package com.daeseong.bluetooth_test

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
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
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity4 : AppCompatActivity() {

    private val TAG = MainActivity4::class.java.simpleName

    private var tv1: TextView? = null
    private var button1 : Button? = null

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null
    private var bluetoothAdapter: BluetoothAdapter? = null

    private var stringBuilder: StringBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        stringBuilder = java.lang.StringBuilder()

        tv1 = findViewById<TextView>(R.id.tv1)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            if (checkPermission()) {

                requestAdapter()

                if (bluetoothAdapter != null) {
                    if (bluetoothAdapter!!.isDiscovering) {
                        bluetoothAdapter!!.cancelDiscovery()
                    }
                    bluetoothAdapter!!.startDiscovery()
                }
            }
        }

        //블루투스 검색 상태 확인
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

                if (BluetoothDevice.ACTION_FOUND == intent.action) {

                    //Log.e(TAG, "블루투스 디바이스 찾았을때")

                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    if (device != null && device.name != null) {

                        //Log.e(TAG, device.name + " : " + device.address)
                        stringBuilder!!.append("${device.name} : ${device.address}\n")

                        tv1!!.text = stringBuilder.toString()
                    }
                } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED == intent.action) {

                    //Log.e(TAG, "블루투스 디바이스 검색 시작")

                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == intent.action) {

                    //Log.e(TAG, "블루투스 디바이스 검색 종료")

                } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED == intent.action) {

                    //Log.e(TAG, "블루투스 디바이스 페어링 상태 변화")

                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    if (device!!.bondState == BluetoothDevice.BOND_BONDED) {
                        Log.e(TAG, device.name + " : " + device.address)
                    }
                }
            }
        }

        //블루투스 장치 검색
        intentFilter = IntentFilter()
        intentFilter!!.addAction(BluetoothDevice.ACTION_FOUND)
        intentFilter!!.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        intentFilter!!.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        intentFilter!!.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
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
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_SCAN),1)
                return false
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            //sdk 29
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),2)
                return false
            }

        } else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),3)
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

        Log.e(TAG, "onRequestPermissionsResult: $requestCode")

        //미승인시 재요청
        if (requestCode == 1) {

            if (grantResults.isNotEmpty()) {

                val result = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (!result) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_SCAN)) {
                            requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_SCAN), 1)
                        }
                    }
                }
            }

        } else if (requestCode == 2) {

            if (grantResults.isNotEmpty()) {

                val result = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (!result) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
                        }
                    }
                }
            }
        } else if (requestCode == 3) {

            if (grantResults.isNotEmpty()) {

                val result = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (!result) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        3
                    )
                }
            }
        }
    }
}