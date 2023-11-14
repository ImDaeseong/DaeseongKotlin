package com.daeseong.appwidgetprovider_test

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class NewAppWidget : AppWidgetProvider() {

    companion object {
        private val TAG = NewAppWidget::class.java.simpleName
        const val CRT_ITEM = "com.daeseong.appwidgetprovider_test.CREATE_ITEM"
        const val NOR_ITEM = "com.daeseong.appwidgetprovider_test.NORMAL_ITEM"

        // AppWidget을 업데이트하는 함수
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)

            // 이미지 리소스 설정
            views.setImageViewResource(R.id.appwidget_img, R.drawable.qr)

            // 이미지 클릭에 대한 PendingIntent 설정
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            views.setOnClickPendingIntent(R.id.appwidget_img, pendingIntent)

            // AppWidget 매니저에게 업데이트 지시
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    // 브로드캐스트 수신 처리
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action != null) {

            when (intent.action) {
                CRT_ITEM -> {
                    val sMessage = intent.getStringExtra(CRT_ITEM)
                    setQRCode(sMessage, context)
                }
                NOR_ITEM -> {
                    setQRCode(context)
                }
            }
        }
    }

    // 모든 위젯 인스턴스 업데이트
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { appWidgetId ->
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    // 위젯 활성화 및 비활성화 라이프사이클 메서드
    override fun onEnabled(context: Context) {
        // 첫 번째 위젯 인스턴스가 추가될 때 실행할 코드
    }

    override fun onDisabled(context: Context) {
        // 마지막 위젯 인스턴스가 삭제될 때 실행할 코드
    }

    // QR 코드를 위젯에 설정하는 함수
    private fun setQRCode(sMessage: String?, context: Context) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)

        try {

            val bitmap = QRinfo.createQRCode(sMessage, 220, 220)
            if (bitmap != null) {
                remoteViews.setImageViewBitmap(R.id.appwidget_img, bitmap)
            } else {
                remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
            }

        } catch (ex: Exception) {
            remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
        }

        // AppWidget 매니저에게 위젯 업데이트 지시
        appWidgetManager.updateAppWidget(ComponentName(context, NewAppWidget::class.java), remoteViews)
    }

    // 기본 QR 코드를 위젯에 설정하는 함수
    private fun setQRCode(context: Context) {

        val appWidgetManager = AppWidgetManager.getInstance(context)
        val remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)

        // 이미지 리소스를 기본 QR 코드로 설정
        remoteViews.setImageViewResource(R.id.appwidget_img, R.drawable.qr)

        // AppWidget 매니저에게 위젯 업데이트 지시
        appWidgetManager.updateAppWidget(ComponentName(context, NewAppWidget::class.java), remoteViews)
    }
}
