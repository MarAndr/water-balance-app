package com.example.mywaterapp.alarmManager.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mywaterapp.alarmManager.utils.Constants
import com.example.mywaterapp.alarmManager.utils.RandomIntUtil
import com.example.mywaterapp.alarmManager.receiver.AlarmReceiver

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setExactAlarm(timeInMillis: Long){
        setAlarm(
            timeInMillis,
            getPendingIntent(getIntent().apply {
                action = Constants.ACTION_SET_EXACT_ALARM
                putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
            }
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setHourAlarm(timeInMillis: Long){
        setAlarm(
            timeInMillis,
            getPendingIntent(getIntent().apply {
                action = Constants.ACTION_SET_HOUR_ALARM
                putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
            }
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun set2HourAlarm(timeInMillis: Long){
        setAlarm(
                timeInMillis,
                getPendingIntent(getIntent().apply {
                    action = Constants.ACTION_SET_2HOUR_ALARM
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
                }
                )
        )
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun set3HourAlarm(timeInMillis: Long){
        setAlarm(
                timeInMillis,
                getPendingIntent(getIntent().apply {
                    action = Constants.ACTION_SET_3HOUR_ALARM
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
                }
                )
        )
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent){
        alarmManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            } else{
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    private fun getIntent()= Intent(context, AlarmReceiver::class.java)

    private fun getPendingIntent(intent: Intent) =
        PendingIntent.getBroadcast(
            context,
            RandomIntUtil.getRandom(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


}