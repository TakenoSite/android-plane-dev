package com.example.neonclock.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.example.neonclock.data.SystemClockTimeProvider

class ClockWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        updateWidgets(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == Intent.ACTION_TIME_CHANGED || intent.action == Intent.ACTION_TIMEZONE_CHANGED) {
            val manager = AppWidgetManager.getInstance(context)
            val widget = ComponentName(context, ClockWidgetProvider::class.java)
            val ids = manager.getAppWidgetIds(widget)
            updateWidgets(context, manager, ids)
        }
    }

    private fun updateWidgets(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val renderer = ClockWidgetRenderer(SystemClockTimeProvider())
        appWidgetIds.forEach { widgetId ->
            appWidgetManager.updateAppWidget(widgetId, renderer.render(context))
        }
    }
}
