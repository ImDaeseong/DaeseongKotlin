package com.daeseong.appwidgetprovider_test

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.daeseong.appwidgetprovider_test.QRinfo.CreateQRrcode

class NewAppWidget : AppWidgetProvider() {

    companion object {
        private val TAG = NewAppWidget::class.java.simpleName
        const val CRT_ITEM = "com.daeseong.appwidgetprovider_test.CREATE_ITEM"
        const val NOR_ITEM = "com.daeseong.appwidgetprovider_test.NORMAL_ITEM"
        
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

            val views = RemoteViews(context.packageName, R.layout.new_app_widget)

            views.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            views.setOnClickPendingIntent(R.id.appwidget_img, pendingIntent)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action != null) {
            if (intent.action == CRT_ITEM) {
                val sMessage = intent.getStringExtra(CRT_ITEM)
                setQRCode(sMessage, context)
            } else if (intent.action == NOR_ITEM) {
                setQRCode(context)
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

    private fun setQRCode(sMessage: String?, context: Context) {

        val appWidgeManger = AppWidgetManager.getInstance(context)
        val remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)

        /*
        //이미지 변경 여부를 확인 하기 위해
        if (sMessage != null) {
            if(sMessage.isEmpty()){
                remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
            } else {
                remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qrview)
            }
        }
        */

        try {

            val bitmap = CreateQRrcode(sMessage, 220, 220)
            if (bitmap != null) {
                remoteViews.setImageViewBitmap(R.id.appwidget_img, bitmap)
            } else {
                remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
            }
        } catch (ex: Exception) {
            remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
        }

        appWidgeManger.updateAppWidget(ComponentName(context, NewAppWidget::class.java), remoteViews)
    }

    private fun setQRCode(context: Context) {

        val appWidgeManger = AppWidgetManager.getInstance(context)
        val remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)
        remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
        appWidgeManger.updateAppWidget(ComponentName(context, NewAppWidget::class.java), remoteViews)
    }
}