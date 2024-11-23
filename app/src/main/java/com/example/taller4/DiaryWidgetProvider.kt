package com.example.taller4

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews

class DiaryWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == "com.example.taller4.UPDATE_WIDGET") {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context, DiaryWidgetProvider::class.java))
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.widget_diary)

        // Get diary entries and motivational phrases
        val diaryEntries = getDiaryEntries(context)
        val motivationalPhrases = listOf(
            "¡Mantén una actitud positiva!",
            "¡Sigue adelante!",
            "¡Nunca te rindas!",
            "¡Cree en ti mismo!",
            "¡El éxito está a la vuelta de la esquina!",
            "¡Cada día es una nueva oportunidad!",
            "¡Haz lo mejor que puedas!",
            "¡La perseverancia es la clave!",
            "¡Confía en el proceso!",
            "¡Eres más fuerte de lo que piensas!"
        )

        // Select a random entry and phrase
        val randomEntry = diaryEntries.randomOrNull() ?: "No hay entradas disponibles"
        val randomPhrase = motivationalPhrases.random()

        views.setTextViewText(R.id.widgetTextView, "Última entrada: $randomEntry")
        views.setTextViewText(R.id.widgetMotivationTextView, randomPhrase)

        // Set up the intent that updates the widget
        val intentUpdate = Intent(context, DiaryWidgetProvider::class.java).apply {
            action = "com.example.taller4.UPDATE_WIDGET"
        }
        val pendingIntentUpdate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, 0, intentUpdate, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        views.setOnClickPendingIntent(R.id.widgetButton, pendingIntentUpdate)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getDiaryEntries(context: Context): List<String> {
        val sharedPreferences = context.getSharedPreferences("diary_entries", Context.MODE_PRIVATE)
        val entries = sharedPreferences.getStringSet("entries", setOf()) ?: setOf()
        return entries.toList()
    }
}