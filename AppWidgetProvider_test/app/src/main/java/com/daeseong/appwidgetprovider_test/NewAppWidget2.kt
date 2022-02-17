package com.daeseong.appwidgetprovider_test

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class NewAppWidget2 : AppWidgetProvider() {

    companion object {
        private val TAG = NewAppWidget2::class.java.simpleName
        const val ACTION_BTN = "CLICKBUTTON"
        const val ACTION_TXT = "CLICKTEXT"

        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

            val views = RemoteViews(context.packageName, R.layout.new_app_widget2)

            views.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
            views.setTextViewText(R.id.appwidget_tv, "Qr 위젯")

            val intent1 = Intent(context, NewAppWidget2::class.java)
            intent1.action = ACTION_BTN
            val pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, 0)
            views.setOnClickPendingIntent(R.id.appwidget_img, pendingIntent1)

            val intent2 = Intent(context, NewAppWidget2::class.java)
            intent2.action = ACTION_TXT
            val pendingIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0)
            views.setOnClickPendingIntent(R.id.appwidget_tv, pendingIntent2)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action != null) {
            if (intent.action == ACTION_BTN || intent.action == ACTION_TXT) {

                if (MainActivity().getMainActivity() == null) {
                    val activity = Intent(context, MainActivity::class.java)
                    activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(activity)
                } else {
                    MainActivity().getMainActivity()!!.func_Main()
                }
            }
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {}
    override fun onDisabled(context: Context) {}
}