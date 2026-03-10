package com.example.neonclock.widget

import android.content.Context
import android.widget.RemoteViews
import com.example.neonclock.R
import com.example.neonclock.domain.ClockTimeProvider

class ClockWidgetRenderer(
    private val timeProvider: ClockTimeProvider
) {
    fun render(context: Context): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_clock)
        remoteViews.setTextViewText(R.id.text_clock_time, timeProvider.currentTime())
        return remoteViews
    }
}
