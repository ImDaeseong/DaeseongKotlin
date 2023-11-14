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

        // AppWidget을 업데이트하는 함수
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget2)

            // 이미지 및 텍스트 리소스 설정
            views.setImageViewResource(R.id.appwidget_img, R.drawable.qr)
            views.setTextViewText(R.id.appwidget_tv, "Qr 위젯")

            // 이미지에 대한 클릭 PendingIntent 설정
            val intent1 = Intent(context, NewAppWidget2::class.java)
            intent1.action = ACTION_BTN
            val pendingIntent1 = PendingIntent.getBroadcast(context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.appwidget_img, pendingIntent1)

            // 텍스트에 대한 클릭 PendingIntent 설정
            val intent2 = Intent(context, NewAppWidget2::class.java)
            intent2.action = ACTION_TXT
            val pendingIntent2 = PendingIntent.getBroadcast(context,0, intent2,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.appwidget_tv, pendingIntent2)

            // AppWidget 매니저에게 업데이트 지시
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    // 브로드캐스트 수신 처리
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
}
