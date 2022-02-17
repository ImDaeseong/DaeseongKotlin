package com.daeseong.appwidgetprovider_test

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null

    private var mainActivity: MainActivity? = null
    fun getMainActivity(): MainActivity? {
        return mainActivity
    }

    private fun setMainActivity(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMainActivity(this)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            //바로가기 생성
            //setWidgetShortcut1(this)
            setWidgetShortcut2(this)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            val intent = Intent(this, NewAppWidget::class.java)
            intent.action = NewAppWidget.CRT_ITEM
            intent.putExtra(NewAppWidget.CRT_ITEM, "0123456789")
            this.sendBroadcast(intent)
        }

        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

            val intent = Intent(this, NewAppWidget::class.java)
            intent.action = NewAppWidget.NOR_ITEM
            this.sendBroadcast(intent)
        }
    }

    private fun setWidgetShortcut1(context: Context) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val appWidgetManager: AppWidgetManager = context.getSystemService(AppWidgetManager::class.java)

                if (appWidgetManager.isRequestPinAppWidgetSupported) {

                    //위젯 없을 경우에만 추가
                    if (appWidgetManager.getAppWidgetIds(ComponentName(context, NewAppWidget::class.java)).isEmpty()) {
                        val widgetProvider = ComponentName(context, NewAppWidget::class.java)
                        val pinnedWidgetCallbackIntent = Intent(this, MainActivity::class.java)
                        val successCallback = PendingIntent.getBroadcast(this,0, pinnedWidgetCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT)
                        appWidgetManager.requestPinAppWidget(widgetProvider, null, successCallback)
                    }
                }
            }

        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }

    private fun setWidgetShortcut2(context: Context) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val appWidgetManager: AppWidgetManager = context.getSystemService(AppWidgetManager::class.java)

                if (appWidgetManager.isRequestPinAppWidgetSupported) {

                    //위젯 없을 경우에만 추가
                    if (appWidgetManager.getAppWidgetIds(ComponentName(context, NewAppWidget2::class.java)).isEmpty()) {
                        val widgetProvider = ComponentName(context, NewAppWidget2::class.java)
                        val pinnedWidgetCallbackIntent = Intent(this, MainActivity::class.java)
                        val successCallback = PendingIntent.getBroadcast(this,0, pinnedWidgetCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT)
                        appWidgetManager.requestPinAppWidget(widgetProvider, null, successCallback)
                    }
                }
            }

        } catch (ex: Exception) {
            Log.e(TAG, ex.message.toString())
        }
    }

    fun func_Main() {

        val activity = Intent(this, MainActivity::class.java)
        activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(activity)

        /*
        //현재 상태 유지
        val activity = Intent(this, MainActivity::class.java)
        activity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(activity);
        */
    }
}