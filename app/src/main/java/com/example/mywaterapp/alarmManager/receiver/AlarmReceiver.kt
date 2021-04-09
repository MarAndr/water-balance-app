package com.example.mywaterapp.alarmManager.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.example.mywaterapp.alarmManager.service.AlarmService
import com.example.mywaterapp.R
import com.example.mywaterapp.alarmManager.NotificationChannels
import com.example.mywaterapp.alarmManager.utils.Constants
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class AlarmReceiver: BroadcastReceiver() {


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onReceive(context: Context, intent: Intent) {
        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)
        when(intent.action){
            Constants.ACTION_SET_EXACT_ALARM -> {
                Timber.d("test alarm service exact alarm")
            buildNotification(context)
            }
            Constants.ACTION_SET_HOUR_ALARM -> {
                val cal = Calendar.getInstance().apply {
                    Timber.d("test")
                    this.timeInMillis = timeInMillis + TimeUnit.SECONDS.toMillis(5)
                }
                AlarmService(context).setHourAlarm(cal.timeInMillis)
                buildNotification(context)
            }
            Constants.ACTION_SET_2HOUR_ALARM -> {
                val cal = Calendar.getInstance().apply {
                    this.timeInMillis = timeInMillis + TimeUnit.MINUTES.toMillis(120)
                }
                AlarmService(context).set2HourAlarm(cal.timeInMillis)
                buildNotification(context)
            }
            Constants.ACTION_SET_3HOUR_ALARM -> {
                val cal = Calendar.getInstance().apply {
                    this.timeInMillis = timeInMillis + TimeUnit.MINUTES.toMillis(180)
                }
                AlarmService(context).set3HourAlarm(cal.timeInMillis)
                buildNotification(context)
            }
        }
    }


    private fun buildNotification(context: Context){
        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.bottle_large)
        val notification = NotificationCompat.Builder(context, NotificationChannels.NEWS_CHANNEL_ID)
            .setContentTitle(context.getString(R.string.notification_message))
            .setSmallIcon(R.drawable.ic_baseline_settings)
            .setLargeIcon(largeIcon)
            .build()

        NotificationManagerCompat.from(context).notify(1, notification)
    }


}