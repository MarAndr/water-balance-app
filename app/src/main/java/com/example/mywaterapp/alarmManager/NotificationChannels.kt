package com.example.mywaterapp.alarmManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

object NotificationChannels {

   const val MESSAGE_CHANNEL_ID = "messages"
   const val NEWS_CHANNEL_ID = "news"

    fun create(context: Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createMessageChannel(context)
            createNewsChannel(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMessageChannel(context: Context){
        val name = "Messages"
        val channelDescription = "Urgent messages"
        val priority = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(MESSAGE_CHANNEL_ID, name, priority).apply {
            description = channelDescription
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes)
        }
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNewsChannel(context: Context){
        val name = "News"
        val channelDescription = "Ordinary news"
        val priority = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NEWS_CHANNEL_ID, name, priority).apply {
            description = channelDescription
        }
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }
}