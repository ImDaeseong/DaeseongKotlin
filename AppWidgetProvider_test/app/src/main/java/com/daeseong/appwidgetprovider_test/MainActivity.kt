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

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

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

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            setWidgetShortcut2(this)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            sendWidgetBroadcast(NewAppWidget.CRT_ITEM, "0123456789")
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            sendWidgetBroadcast(NewAppWidget.NOR_ITEM, null)
        }
    }

    private fun setWidgetShortcut1(context: Context) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val appWidgetManager: AppWidgetManager = context.getSystemService(AppWidgetManager::class.java)
                if (appWidgetManager.isRequestPinAppWidgetSupported) {

                    if (appWidgetManager.getAppWidgetIds(ComponentName(context, NewAppWidget::class.java)).isEmpty() ) {
                        val widgetProvider = ComponentName(context, NewAppWidget::class.java)
                        val pinnedWidgetCallbackIntent = Intent(context, MainActivity::class.java)
                        val successCallback = PendingIntent.getBroadcast(context,0, pinnedWidgetCallbackIntent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                        appWidgetManager.requestPinAppWidget(widgetProvider,null, successCallback)
                    }
                }
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun setWidgetShortcut2(context: Context) {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val appWidgetManager: AppWidgetManager = context.getSystemService(AppWidgetManager::class.java)

                if (appWidgetManager.isRequestPinAppWidgetSupported) {

                    if (appWidgetManager.getAppWidgetIds(ComponentName(context, NewAppWidget2::class.java)).isEmpty()) {

                        val widgetProvider = ComponentName(context, NewAppWidget2::class.java)
                        val pinnedWidgetCallbackIntent = Intent(context, MainActivity::class.java)
                        val successCallback = PendingIntent.getBroadcast(context,0, pinnedWidgetCallbackIntent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                        appWidgetManager.requestPinAppWidget(widgetProvider,null, successCallback)
                    }
                }
            }

        } catch (ex: Exception) {
            Log.e(tag, ex.message.toString())
        }
    }

    private fun sendWidgetBroadcast(action: String, extra: String?) {

        val intent = Intent(this, NewAppWidget::class.java)
        intent.action = action
        extra?.let {
            intent.putExtra(action, it)
        }
        sendBroadcast(intent)
    }

    fun func_Main() {

        val activity = Intent(this, MainActivity::class.java)
        activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(activity)
    }
}
